import java.util.Arrays;

public class SubsetSum {
    int s;
    public static void main(String args[]) {
        int n = 100, k = 13;
        int arr[] = new int[k];
        long t = System.currentTimeMillis();
        combinationUtil(n, k, 0, arr, 0);
        System.out.println("Time taken - " + (System.currentTimeMillis() - t));
        
    }

    static void combinationUtil(int n, int r, int index, int data[], int i)
    {
        // Current cobination is ready, print it
        int s = 0;
        if (index == r) {
            for (int x: data) s += x;
            //if (s == n) System.out.println(Arrays.toString(data));
            return;
        }

        // When no more elements are there to put in data[]
        if (i >= n)
        return;

        // current is included, put next at next location
        data[index] = i+1;
        for (int j = 0; j <= index; j++) s += data[j];
        if (s > n) return;
        combinationUtil(n, r, index + 1, data, i + 1);

        // current is excluded, replace it with next
        // (Note that i+1 is passed, but index is not
        // changed)
        combinationUtil(n, r, index, data, i + 1);
    }
}