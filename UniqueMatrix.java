import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueMatrix {
    public static void main(String args[]) {
        generateAndPrintArray(2, 3, 0);
    }

    static void generateAndPrintArray(int row, int col, int start) {
        int matrix[][] = new int[row][col], i = 0, j, k, l;
        for (; i < row; i++) {
            Set<Integer> set = new HashSet<>();
            for (j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    matrix[0][0] = start;
                    set.add(start);
                    continue;
                }
                for (k = 0; k < col; k++) {
                    if (set.contains(k)) continue;
                    boolean numPresentInColumn = false;
                    for (l = i-1; l >= 0; l--) {
                        if (matrix[l][j] == k) {
                            numPresentInColumn = true;
                            break;
                        }
                    }
                    if (!numPresentInColumn) {
                        matrix[i][j] = k;
                        System.out.printf("[LOG] i = %d, j = %d, k = %d\n", i, j, k);
                        set.add(k);
                        break;
                    }
                }
            }
        }
        for (i = 0; i < row; i++)
            System.out.println(Arrays.toString(matrix[i]));
    }
}