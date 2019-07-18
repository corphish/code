import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class NotificationGenerator {

    public static void main(String[] args) {
        String[] sender = {"a", "b", "a", "b", "c"}, receiver = {"b", "a", "b", "a", "a"}, date ={
            "2018-02-02 12:23:00", 
            "2018-01-31 12:23:00", 
            "2018-01-29 12:23:00", 
            "2018-01-27 12:22:59", 
            "2018-02-01 12:23:00"
        };

        String d = "2018-04-02 22:22:22";
        long l = getDateFromString(d);
        System.out.printf("Long - %d, LongToString - %s, LongToString with 2 days added - %s\n", l, getDateFromLong(l), getDateFromLong(l + TWO_DAYS_MS));

        collectReminders(sender, receiver, date);
        analyseReminders();
    }

    // Date conversion methods
    static final long TWO_DAYS_MS = 2 * 24 * 60 * 60 * 1000;
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static long getDateFromString(String date) {
        Date d = null;
        try {
            d = simpleDateFormat.parse(date);
        } catch (ParseException ignored) {}

        return d == null ? 0 : d.getTime();
    }

    static String getDateFromLong(long date) {
        Date d = new Date(date);
        
        return simpleDateFormat.format(d);
    }

    static class Reminder {
        String personToBeResponded;
        long dueDate;

        Reminder(String personToBeResponded, long dueDate) {
            this.personToBeResponded = personToBeResponded;
            this.dueDate = dueDate;
        }

        @Override
        public String toString() {
            return String.format(Locale.getDefault(), "{%s : %d}", personToBeResponded, dueDate);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Reminder)) return false;

            Reminder r = (Reminder) o;

            return this.personToBeResponded.equals(r.personToBeResponded) && this.dueDate == r.dueDate;
        }
    }

    static Map<String, List<Reminder>> reminderMap = new HashMap<>(), map;

    static void collectReminders(String[] sender, String[] receiver, String[] date) {
        int len = sender.length, i = 0;

        for (; i < len; i++) {
            List<Reminder> reminders = reminderMap.get(receiver[i]);
            if (reminders == null) reminders = new ArrayList<>();
            reminders.add(new Reminder(sender[i], getDateFromString(date[i]) + TWO_DAYS_MS));
            reminderMap.put(receiver[i], reminders);
        }

        System.out.println(reminderMap);
    }

    static Map<String, List<Reminder>> copyOf(Map<String, List<Reminder>> oldMap) {
        Map<String, List<Reminder>> newMap = new HashMap<>();
        for (Map.Entry<String, List<Reminder>> entry : oldMap.entrySet()) {
            newMap.put(entry.getKey(), entry.getValue() == null ? null : new ArrayList<>(entry.getValue()));
        }

        return newMap;
    }

    static void analyseReminders() {
        int repliesMade = 0;
        map = copyOf(reminderMap);

        for (Map.Entry<String, List<Reminder>> entry : reminderMap.entrySet()) {
            List<Reminder> reminders = entry.getValue();

            if (reminders == null) continue;

            for (Reminder reminder : reminders) {
                //System.out.println(reminder);
                //System.out.println("==========================================");
                // Get the map key reminder.personToBeResponded, and see if there are any responses made to key within reminder.dueDae to entry.getKey()
                List<Reminder> ackReminders = reminderMap.get(reminder.personToBeResponded);
                if (ackReminders == null) continue;

                for (Reminder ackReminder : ackReminders) {
                    //System.out.println(ackReminder);
                    long diff = ackReminder.dueDate - reminder.dueDate;
                    //System.out.println("Ackdue - " + ackReminder.dueDate + ", reminder due - " + reminder.dueDate + ", diff - " + (ackReminder.dueDate - reminder.dueDate) + ", limit - " + TWO_DAYS_MS);
                    if (diff >= 0 && diff <= TWO_DAYS_MS) {
                        repliesMade++;
                        removeFromMapSafe(entry.getKey(), reminder);
                        //removeFromMapSafe(map, reminder.personToBeResponded, ackReminder);
                    }
                }
                //System.out.println("==========================================");
            }
        }

        //System.out.println("Replies made - " + repliesMade);
        //System.out.println(map);

        buildNotifications();
    }

    static void removeFromMapSafe(String sender, Reminder r) {
        List<Reminder> list = map.get(sender);
        list.remove(r);
        map.put(sender, list);
    }

    static List<String> buildNotifications() {
        List<String> notifications = new ArrayList<>();
        for (Map.Entry<String, List<Reminder>> entry : map.entrySet()) {
            List<Reminder> list = entry.getValue();

            if (list == null) continue;

            for (Reminder r : list) {
                notifications.add(String.format(Locale.getDefault(), "%s. %s please respond to %s", getDateFromLong(r.dueDate), entry.getKey(), r.personToBeResponded));
            }
        }

        Collections.sort(notifications, (a, b) -> a.split("\\.")[0].compareTo(b.split("\\.")[0]));

        for (String notification : notifications)
            System.out.println(notification);

        return notifications;
    }
}