import java.util.Map;
import java.util.HashMap;

public class DiceRolls2 {
    public static void main(String args[]) {
        int[][] dice = new int[100][100];
        int c = 50000;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                dice[i][j] = c;
            }
        }

        new Solver().diceRolls2(dice);
    }

    static class Solver {
        int i=1, j, n;
        long diceRolls2(int[][] d) {
            Map<Long, Long> t = new HashMap<>(), w;
            for (int f:d[0]) t.put(new Long(f), 1L/*(t.get(f) == null ? 0 : t.get(f)) + 1*/);
            
            for (n = d.length; i < n; i++) {
                w = new HashMap<>();
                for (int f: d[i]) {
                    for (Map.Entry<Long, Long> e:t.entrySet()) {
                        Long index = f + e.getKey(), v = w.get(index);
                        w.put(index, (v == null ? 0 : v) + e.getValue());
                    }
                }
                t = w;
                //System.out.println(Arrays.toString(t));
            }
            long m = 0, k = 1<<24;
            for (Map.Entry<Long, Long> e:t.entrySet()) {
                //System.out.println(e.getKey() + " -> " + e.getValue());
                if (e.getValue() > m) {
                    m = e.getValue();
                    k = e.getKey();
                }
                else if (e.getValue() == m) {
                    if (e.getKey() < k) {
                        m = e.getValue();
                        k = e.getKey();
                    }
                }
            }
            
            System.out.println(k + " -> " + m);
            System.out.println(Long.MAX_VALUE - m);
            return k;
        }
        
        /*int s = 1<<18, i, j, n, k;
        int diceRolls2(int[][] d) {
            long t[][] = new long[n = d.length][s];
            for (int f:d[0]) t[0][f]++;
            
            for (i = 1; i < n; i++) {
                for (int f: d[i]) {
                    for (j = 0; j < s-f; j++) {
                        t[i][f + j] += t[i-1][j];
                    }
                }
            }
            long m = 0;
            for (i = 0; i < s; i++) {
                if (t[n - 1][i] > m) {
                    m = t[n - 1][i];
                    k = i;
                }
            }
            
            return k;
        }*/
        
    }
}