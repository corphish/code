import java.util.*;

public class SleddingExtreme {
    public static void main(String[] args) {
        Integer arr[] = new Integer[20000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = i + 1;

        long t = System.currentTimeMillis();
        System.out.println(sleddingHillsExtreme(arr, 10000000));
        System.out.println((System.currentTimeMillis() - t) + "ms");
    }

    static long sleddingHillsExtreme(Integer[] E, int m) {
        Arrays.sort(E, (a, b) -> b - a);
        long sum = 0;
        int x = 0; 
        for (; m > 0; m--) {
            int i = 0;
            for (; (i + 1) < E.length && E[i] <= E[i+1]; i++) {
                // x = E[i] == E[i + 1] ? i : x;
            }
            sum += Math.max(0, E[i]);
            E[i]--;
            // System.out.println(Arrays.toString(E));
            if (E[E.length - 1] == 0 && E[0] == 0)  break;
        }
        
        return sum % 1000000007;
    }    
}