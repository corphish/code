import java.util.*;

public class SequenceElement {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            List<Integer> l = generateRandomList();
            int z = matchIndex(l);
            System.out.println(l.subList(0, 5) + " => " + z);
        }
    }

    private static List<Integer> generateRandomList() {
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int r = (int) Math.floor(Math.random() * 10);
            l.add(r);
        }

        return l;
    }

    private static void extendList(List<Integer> l) {
        for (int j = 0 ; j < 5; j++) {
            int k = 0;
            for (int i = 0; i < 5; i++) {
                k += l.get(l.size() - 1 - i);
            }
            l.add(k % 10);
        }
        
    }

    private static int matchIndex(List<Integer> l) {
        int match = -1;
        int k = 0, s = 5;;
        while (match < 0) {
            extendList(l);
            boolean b = true;
            for (int i = 0; i < 5; i++) {
                b &= l.get(i) == l.get(s + i);
            }

            if (b) match = s;
            s++;
        }

        return match;
    }
} 