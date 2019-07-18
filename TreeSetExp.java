import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class TreeSetExp {
    public static void main(String args[]) {
        //int a[] = IntStream.range(0, 1<<16).toArray();
        int i = 0, k;
        long t1 = 0, t2 = 0, m = System.currentTimeMillis();
        /*TreeSet s = new TreeSet();
        for (int j = a.length - 1; j >= 0; j--) {
            i++;
            long t = System.nanoTime();
            s.add(a[j]);
            t1 = Math.max(t1, System.nanoTime() - t);
            t = System.nanoTime();
            k = s.headSet(a[j]).size();
            t2 = Math.max(t2, System.nanoTime() - t);
            //if (a[j] % 1000 == 0) System.out.println(a[j]);
        }
        System.out.println(t1 + " ns");
        System.out.println(t2 + " ns");*/
        //height(a);
        Arrays.sort(a);
        System.out.println((System.currentTimeMillis() - m) + " ms");
    }

    static int[] height(int[] t) {
        int l = t.length, i = l - 1, k = 0, a[] = new int[1<<16], p=0,j;
        TreeSet s = new TreeSet();
        for (; i >= 0; i--) {
            //long time = System.nanoTime();
            if (!s.add(t[i])) a[t[i]]++;       
            k = s.headSet(t[i]).size();
            //System.out.println(System.nanoTime() - time); 
            p = 0;
            j = 0;
            for (int x:a) {
                if (j++ >= t[i]) break;
                p += x;
            }
            //System.out.println(t[i] + ", " + k + " -> "+ p  + " = " + Arrays.toString(a));
            t[i] = k + p;
        }
        return t;
    }
}