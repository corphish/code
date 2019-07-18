public class FactorialTrailingZeroes {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = 0, f = 0;

        for (; n > 1; n--) {
            for (int k = n; k % 2 == 0 || k % 5 == 0;) {
                if (k % 2 == 0) {
                    k /= 2;
                    t++;
                }
                if (k % 5 == 0) {
                    k /= 5;
                    f++;
                }
            } 
        }

        System.out.println(Math.min(t, f));
    }
}