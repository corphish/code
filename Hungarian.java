import java.util.Arrays;
import java.util.Set;

import java.util.LinkedHashSet;

public class Hungarian {
    public static void main(String args[]) {
        /*int matrix[][] = {
            {1,1,1,2},
            {1,1,1,2},
            {1,1,1,2},
            {1,1,1,10}
        };*/

        /*int matrix[][] = {
            {25, 40, 35},
            {40, 60, 35},
            {20, 40, 25},
        };*/

        /*int matrix[][] = {
            {64, 18, 75},
            {97, 60, 24},
            {87, 63, 15},
        };*/

        /*int matrix[][] = {
            {80,40,50,46}, 
            {40,70,20,25}, 
            {30,10,20,30}, 
            {35,20,25,30},
        };*/

        int matrix[][] = {
            {10,19,8,15}, 
            {10,18,7,17}, 
            {13,16,9,14}, 
            {12,19,8,18}, 
            {14,17,10,19},
        };

        /*int matrix[][] = {
            {1500,4000,4500}, 
            {2000,6000,3500}, 
            {2000,4000,2500},
        };*/

        /*int matrix[][] = {
            {73,94,85,46,87}, 
            {1,88,80,42,56}, 
            {27,65,76,2,7}, 
            {73,26,41,42,83}, 
            {3,69,53,39,92},
        };*/

        /*int matrix[][] = {
            {55,76,23,84,43,8,65}, 
            {1,78,80,94,95,96,24}, 
            {32,98,29,33,93,89,35}, 
            {70,7,35,71,21,5,89}, 
            {95,79,11,97,74,27,22}, 
            {5,75,90,81,77,9,81}, 
            {67,66,61,24,33,72,15},
        };*/

        System.out.println("Solution - " + new HungarianSolver().solveMatrix(matrix));
    }

    private static class HungarianSolver {
        private final int SOLUTION_INDICATOR    = -1;
        private final int CANCEL_INDICATOR      = -2;

        private final boolean DEBUG             = true;

        private int[][] optimalMatrix, lastMarkedMatrix;

        public void printArray(String msg ,int[][] arr) {
            if (DEBUG) {
                System.out.println("*************************");
                System.out.println(msg);
                System.out.println("*************************");
                for (int[] row: arr) System.out.println(Arrays.toString(row));
            }
        }

        public void printMsg(Object msg) {
            if (DEBUG) {
                System.out.println(msg);
            }
        }

        public int[][] fixedMatrix(int[][] matrix) {
            if (matrix.length == matrix[0].length) return matrix;

            // If rows greater than columns
            int newMatrix[][];
            if (matrix.length > matrix[0].length) {
                newMatrix = new int[matrix.length][matrix.length];
            } else {
                newMatrix = new int[matrix[0].length][matrix[0].length];
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) newMatrix[i][j] = matrix[i][j];
            }
            printArray("Fixed matrix", newMatrix);
            return newMatrix;
        }

        public int solveMatrix(int[][] matrix) {
            matrix = fixedMatrix(matrix);
            int newM[][] = clone(matrix);

            while (true) {
                printArray("Input matrix", matrix);
                printArray("Cloned matrix", newM);
                if (cancelRows(newM)) break;

                printArray("Rows canceled matrix", newM);
                if (cancelColumns(newM)) break;
                printArray("Column canceled matrix", newM);
                drawLinesAndAllThoseShitz(newM);
                break;
            }
            return generateSolution(matrix);
        }

        public boolean cancelRows(int[][] matrix) {
            boolean retVal;
            // Find the smallest of each row and subtract
            for (int i = 0; i < matrix.length; i++) {
                int min = matrix[i][0];

                for (int x: matrix[i]) {
                    if (x < min) min = x;
                }

                for (int j = 0; j < matrix[i].length; j++) matrix[i][j] -= min;
            }

            printArray("Cancel ROWS", matrix);
            //printMsg(retVal = isOptimal(matrix));

            return false;
        }

        public boolean cancelColumns(int[][] matrix) {
            boolean retVal;
             // Find the smallest of each column and subtract
             for (int j, i = 0; i < matrix[0].length; i++) {
                int min = matrix[0][i];

                for (j = 0; j < matrix.length; j++) {
                    if (matrix[j][i] < min) min = matrix[j][i];
                }

                for (j = 0; j < matrix.length; j++) matrix[j][i] -= min;
            }

            printArray("Cancel Columns", matrix);
            //printMsg(retVal = isOptimal(matrix));

            return false;
        }

        public boolean isOptimal(int[][] matrix, boolean toClone) {
            int[][] newM;
            if (toClone) newM = clone(matrix);
            else newM = matrix;
            int solution = checkRows(newM) + checkColumns(newM) + 0;
            printMsg("Solution Count = " + solution);

            boolean ret = solution == matrix.length;

            if (ret) optimalMatrix = clone(newM);

            return ret;
        }

        public boolean isOptimal(int[][] matrix) {
            return isOptimal(matrix, true);
        }

        public void makeFinalSolution(int[][] matrix) {
            Set<Integer> s = new LinkedHashSet<>();
            for (int i = 0; i < matrix.length; i++) {
                boolean solutionPresent = false;
                int firstZero = -1;
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == SOLUTION_INDICATOR) {
                        s.add(j);
                        solutionPresent = true;
                        break;
                    }
                    if (firstZero < 0 && matrix[i][j] == 0) {
                        if (!s.contains(j)) {
                            firstZero = j;
                            s.add(j);
                        }
                    }
                }
                if (!solutionPresent && firstZero >= 0) matrix[i][firstZero] = SOLUTION_INDICATOR;
            }

            optimalMatrix = matrix;
            printArray("Made final solution", matrix);
        }

        public int checkRows(int[][] matrix) {
            int solutionCount = 0;
            for (int i = 0; i < matrix.length; i++) {
                int solutionJ = -1, zeroCount = 0, canceledCount = 0;
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        solutionJ = j;
                        zeroCount++;
                    }
                    if (matrix[i][j] == SOLUTION_INDICATOR) {
                        solutionJ = -1;
                        zeroCount = 0;
                        canceledCount = 0;
                        break;
                    }
                    if (matrix[i][j] == CANCEL_INDICATOR) canceledCount++;
                }
                // Solution if zeroCount = 1 and canceledCount = 0
                if (zeroCount == 1 && canceledCount == 0) {
                    matrix[i][solutionJ] = SOLUTION_INDICATOR;
                    cancelColumns(matrix, solutionJ);
                    solutionCount++;
                }
            }
            printArray("Check ROWS", matrix);
            lastMarkedMatrix = matrix;
            return solutionCount;
        }

        public int checkColumns(int[][] matrix) {
            int solutionCount = 0;
            for (int i = 0; i < matrix[0].length; i++) {
                int solutionI = -1, zeroCount = 0, canceledCount = 0;
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[j][i] == 0) {
                        printMsg("Probable Solution " + j + "," + i);
                        solutionI = j;
                        zeroCount++;
                    }
                    if (matrix[j][i] == SOLUTION_INDICATOR) {
                        solutionI = -1;
                        zeroCount = 0;
                        canceledCount = 0;
                        break;
                    }
                    if (matrix[j][i] == CANCEL_INDICATOR) canceledCount++;
                }
                // Solution if zeroCount = 1 and canceledCount = 0
                if (zeroCount == 1 && canceledCount == 0) {
                    printMsg("Accepted solution " + solutionI + "," + i);
                    matrix[solutionI][i] = SOLUTION_INDICATOR;
                    cancelRows(matrix, solutionI);
                    solutionCount++;
                }
            }
            printArray("Check COLUMNS", matrix);
            lastMarkedMatrix = matrix;
            return solutionCount;
        }

        public void cancelColumns(int[][] matrix, int column) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][column] == 0) matrix[i][column] = CANCEL_INDICATOR;
            }
        }

        public void cancelRows(int[][] matrix, int row) {
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[row][i] == 0) matrix[row][i] = CANCEL_INDICATOR;
            }
        }

        public int generateSolution(int[][] oldMatrix) {
            int cost = 0;
            if (optimalMatrix == null) return cost;
            Set<Integer> s = new LinkedHashSet<>();
            printArray("Old Matrix", oldMatrix);
            for (int i = 0; i < oldMatrix.length; i++) {
                for (int j = 0; j < oldMatrix[0].length; j++) {
                    if (optimalMatrix[i][j] == SOLUTION_INDICATOR) {
                        s.add(oldMatrix[i][j]);
                        cost += oldMatrix[i][j];
                    }
                }
            }
            printMsg(s);
            return cost;
        }

        private int[][] clone(int[][] matrix) {
            int [][] myInt = new int[matrix.length][];
            for(int i = 0; i < matrix.length; i++)
                myInt[i] = matrix[i].clone();

            return myInt;
        }

        public void drawLinesAndAllThoseShitz(int[][] matrix) {
            Set<Integer> rowIndexes = new LinkedHashSet<>(), columnIndexes = new LinkedHashSet<>();
            isOptimal(matrix, false);
            printArray("Will drawlines, got", matrix);

            // Find the rows with no optimal solution and mark them
            for (int i = 0; i < matrix.length; i++) {
                boolean solutionPresent = false;
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] <= SOLUTION_INDICATOR) solutionPresent = true;
                }

                if (!solutionPresent) rowIndexes.add(i);
            }

            // foreach row in indexes mark column with 'zero'
            while (true) {
                int count = 0;
                for (int row: rowIndexes) {
                    for (int i = 0; i < matrix[0].length; i++) {
                        if (matrix[row][i] <= 0) {
                            if (columnIndexes.add(i)) count++;
                        }
                    }
                }

                // For each columnIndex, add accepted solution to rowIndexes
                for (int column: columnIndexes) {
                    for (int i = 0; i < matrix.length; i++) {
                        if (matrix[i][column] == SOLUTION_INDICATOR) {
                            if (rowIndexes.add(i)) count++;
                        }
                    }
                }
                if (count == 0) break;
            }
            printMsg("Indexes :");
            printMsg(rowIndexes);
            printMsg(columnIndexes);

            if (columnIndexes.size() + (matrix.length - rowIndexes.size()) == matrix.length) {
                isOptimal(matrix);
                makeFinalSolution(matrix);
                return;
            }

            // Find the min uncovered value
            // Row Indexes are uncovered, column indexes are covered
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < matrix.length; i++) {
                if (!rowIndexes.contains(i)) continue;
                for (int j = 0; j < matrix[0].length; j++) {
                    if (columnIndexes.contains(j)) continue;
                    if (matrix[i][j] > 0 && matrix[i][j] < min) min = matrix[i][j];
                }
            }

            printMsg(min);

            // Subtract from uncovered values
            // Add to dual covered values
            // If any value to be modified turns out to be < 0, make it zero then modify
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] < 0) matrix[i][j] = 0;
                    // If uncovered subtract
                    if (rowIndexes.contains(i) && !columnIndexes.contains(j)) {
                        matrix[i][j] -= min;
                    }
                    // If double interesected, subtract
                    if (!rowIndexes.contains(i) && columnIndexes.contains(j)) {
                        matrix[i][j] += min;
                    }
                    
                    if (matrix[i][j] < 0) matrix[i][j] = 0;
                }
            }

            boolean res;
            printArray("After drawing lines and adding/subracting", matrix);
            printMsg(res = isOptimal(matrix, false));
            printArray("After optimal test", matrix);

            if (!res) drawLinesAndAllThoseShitz(matrix);
        }
    }
}