import java.util.*;

public class HamsterFarm {
    public static void main(String args[]) {
        String[] input = {
            "monika", 
            "karol", 
            "szymon"
        };

        // Map to store those strings which can be placed after the key
        final Map<String, List<String>> nextMap = new HashMap<>();

        int i = 0;
        for (String name: input) {
            int j = 0;
            for (String another: input) {
                if (i != j) {
                    int x = getCommonPoint(name, another);
                    if (x > 0) {
                        List<String> l = nextMap.getOrDefault(name, null);
                        if (l == null) l = new ArrayLis<>();
                        l.add(another);
                        nextMap.put(name, l);
                    }
                }
                j++;
            }
            i++;
        }
    }

    // Returns a positive value if there is a common point in which b can overlap if b is placed after a
    // Returns 0 otherwise
    static int getCommonPoint(String a, String b) {
        int v = 0;
        for (int i = a.length() - 1, j = 1;
             i >= 0 && j <= b.length(); i--, j++) {
            String x = a.substring(i), y = b.substring(0, j);
            if (x.equals(y)) v = j;
        }

        return v;
    }
}