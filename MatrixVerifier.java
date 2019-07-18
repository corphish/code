import java.util.Arrays;

public class MatrixVerifier {

    public static void main(String[] args) {
        /*System.out.println(matrixVerify(
            "010010001", 
            "000111011", 
            "111111111", 3));*/

        System.out.println(tripleMultiply("2334", "1012", "11", 2));
    }

    public static boolean matrixVerify(String a, String b, String c, int n) {
        char v[] = new char[n];
        Arrays.fill(v,'1');
        int tCount = 0;
        for (int i = 0; i < n; i++) {
            String res1 = matrixMultiply(a, matrixMultiply(b, new String(v), n), n);
            String res2 = matrixMultiply(c, new String(v), n);
            
            System.out.println("res1 = " + res1);
            System.out.println("res2 = " + res2);
            tCount += res1.equals(res2) ? 1:0;
            v[i] = '0';
            //if (tCount >= n/2) return true;
        }
        System.out.println(tCount);

        return tCount > n/2;
    }

    public static String matrixMultiply(String squareMatrix, String vectorMatrix, int n) {
        String res = "";
        System.out.println("vector = " + vectorMatrix);
        for (int i = 0; i < n; i++) {
            int x = 0;
            for (int j = 0; j < n; j++) {
                x += (squareMatrix.charAt(i*n + j) - 48) * (vectorMatrix.charAt(j) - 48);
            }
            res += x&1;
        }
        return res;
    }

    public static String tripleMultiply(String a, String b, String c, int n) {
        int store[] = new int[n];
        String res = "";
        for (int i = 0; i < n ;i++) {
            int x = 0;
            for (int j = 0; j < n; j++)
                x += (b.charAt(i*n + j) - 48) * (c.charAt(j) - 48);
            store[i] = x;
        }
        for (int i = 0; i < n ;i++) {
            int y = 0;
            for (int j = 0; j < n; j++)
                y += (a.charAt(i*n + j) - 48) * store[j];
            res += y;
        }

        return res;
    }
}