import java.util.*;

public class PUBG {
    public static void main(String args[]) {
        int dataSet[][] = {
            {2283, 2497, 3712, 5015},
            {1568, 1551, 2774, 3235},
            {1800, 1728, 2908, 3434},
            {1700, 2049, 4023, 5138},
            {1400, 2077, 3300, 4105},
            {1844, 2058, 3198, 4142},
            {1738, 1330, 3780, 4609},
            {2022, 2246, 3344, 4386},
            {1775, 1867, 3041, 3849},
            {1700, 2053, 3027, 3757},
            {1279, 2244, 2615, 3259},
            {2127, 2205, 3176, 4105},
            {1300, 2400, 3751, 4892},
            {1900, 2285, 3328, 4320},
            {1907, 2367, 2915, 3844},
            {1400, 2568, 2372, 3242},
            {1978, 1787, 3069, 3819},
        };

        final Map<CoEffs, Integer> freqMap = new HashMap<>();
        Set<CoEffs> combinedRes = new TreeSet<>((a, b) -> freqMap.get(a) == freqMap.get(b) ? a.compareTo(b) : freqMap.get(b) - freqMap.get(a));

        for (int[] data: dataSet) {
            Set<CoEffs> upMatchCoEffs = new TreeSet<>();
            Set<CoEffs> downMatchCoEffs = new TreeSet<>();

            for (int a = 0; a <= 100; a += 1) {
                for (int b = 0; b <= 100; b += 1) {
                    for (int c = 0; c <= 100; c += 1) {
                        int up = (int) (Math.ceil(a * data[0]) + Math.ceil(b * data[1]) + Math.ceil(c * data[2]));
                        int down = (int) (Math.floor(a * data[0]) + Math.floor(b * data[1]) + Math.floor(c * data[2]));

                        if (up/100 == data[3]) {
                            upMatchCoEffs.add(new CoEffs(a, b, c));
                            // System.out.println("Rounded up match found: " + a + " " + b + " " + c);
                        }

                        if (down/100 == data[3]) {
                            downMatchCoEffs.add(new CoEffs(a, b, c));
                            // System.out.println("Rounded down match found: " + a + " " + b + " " + c);    
                        }
                    }
                }
            }

            for (CoEffs c: upMatchCoEffs) {
                /*if (c.getMaxDeviation() <= 50)*/ freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }

            for (CoEffs c: downMatchCoEffs) {
                /*if (c.getMaxDeviation() <= 50)*/ freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }

        // System.out.println(freqMap);

        for (CoEffs c: freqMap.keySet()) {
            combinedRes.add(c);
        }

        System.out.println("Best matches: ");
        int i = 0, minDiff = 1 << 30;
        CoEffs best = null;
        for (CoEffs c: combinedRes) {
            System.out.println(c + " -> " + freqMap.get(c));
            int diffSum = 0;
            for (int[] d: dataSet) {
                int calc = d[0] * c.a + d[1] * c.b + d[2] * c.c;
                int diff = Math.abs(d[3] - calc);
                diffSum += diff;
                //System.out.printf("%s: Actual: %d, Calculated = %d, Diff = %d\n", Arrays.toString(d), d[3], calc, diff);
            }
            i++;
            if (diffSum < minDiff) {
                minDiff = diffSum;
                best = c;
            }
            //System.out.println();
            if (i > 5) break;
        }

        System.out.println("Best result: " + best);
    }

    static class CoEffs implements Comparable<CoEffs> {
        int a, b, c;

        CoEffs(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        Integer getMaxDeviation() {
            double mean = (a + b + c)/3;

            return (int) (Math.max(Math.abs(mean - a), Math.max(Math.abs(mean - b), Math.abs(mean - c))));
        }

        @Override
        public int compareTo(CoEffs other) {
            return this.getMaxDeviation().compareTo(other.getMaxDeviation());
        }

        public String toString() {
            return String.format(Locale.getDefault(), "%d, %d, %d", a, b, c);
        }
    }
}