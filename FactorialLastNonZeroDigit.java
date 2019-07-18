public class FactorialLastNonZeroDigit {
    public static void main(String args[]) {
        int l = Integer.parseInt(args[0]), m = Integer.parseInt(args[1]);
        long constraint = 50000000;
        int k = Integer.parseInt(args[2]);
        int z = (int) Math.pow(10, k);

        for (int j = l; j <= m; j++) {
            long prod = 1;
            for (int i = 2; i <= j; i++) {
                prod = (prod * i) % constraint;
                prod = trimZeroes(prod);
                // System.out.println(prod);
            }
    
            int r = 0;
            long p = prod;
            while (prod > 0) {
                r = (int) (prod % z);
                if (r != 0) break;
                prod /= 10;
            }
    
            System.out.println(j + " -> " + r + ", " + prod);
        }
        
    }

    private static long trimZeroes(long n) {
        while (n > 0) {
            if (n % 10 != 0) break;
            n /= 10;
        }

        return n;
    } 
}