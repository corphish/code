// Generates all Highly Composite numbers less than 10^50
// Execute with java -Xss1G 

import java.math.BigInteger;
import java.util.*;

public class AntiPrimes {
    public static void main(String args[]) {
        new AntiPrimeGenerator().generateAntiPrimes();
    }

    private static class AntiPrimeGenerator {
        int primeList[] = {2,3,5,7,11,13,17,19,23,29,31,37, 41, 43, 47, 53, 59, 61, 67,71,73,79,83,89,97}, i, combinations[] = new int[primeList.length], D, j, n, c = 1, d, k;
        TreeMap<BigInteger, Integer> store;
        
        void generateAntiPrimes() {
            store = new TreeMap<>();
            store.put(BigInteger.ONE, 1);
            long time = System.currentTimeMillis();
            for (i = 1; i <= 16; i++) {
                Arrays.fill(combinations, 0, primeList.length, 0);
                combinations[0] = i;
                calculateProduct(combinations);
                generateSequence(combinations, 1);
            }
            for (Map.Entry<BigInteger, Integer> e: store.entrySet()) {
                if (e.getValue() > D) {
                    System.out.println(c++ +". " + e.getKey() + " - "+e.getValue());
                    D = e.getValue();
                }
            }
            System.out.print("Time taken (ms) - " + (System.currentTimeMillis() - time));
        }
        
        void generateSequence(int combo[], int p) {
            if (p >= primeList.length) {
                if (combo[p - 1] == combo[0]) return;
                else {
                    for (j = p - 1; j > 0; j--) {
                        if (combo[j] < combo[j-1]) break;
                    }
                    Arrays.fill(combo, j+1, primeList.length, 0);
                    p = j;
                }
            }
            while (combo[p] < combo[p - 1]) {
                combo[p]++;
                if (calculateProduct(combo))
                    n = p + 1;
                else {
                    Arrays.fill(combo, p, primeList.length, combo[p]);
                    n = primeList.length;
                }
                generateSequence(combo, n);
            }
        }
        
        boolean calculateProduct(int x[]) {
            BigInteger p = BigInteger.ONE;
            for (d = 1, k = 0; k < x.length; k++) {
                p = p.multiply(BigInteger.valueOf(primeList[k]).pow(x[k]));
                d *= x[k]+1;
            }

            store.put(p, d);
            
            return p.toString().length() < 50;
        }
    }
}