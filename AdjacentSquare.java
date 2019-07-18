import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;

public class AdjacentSquare {

    public static void main(String args[]) {
        int arr[] = {0, 0, 0, 2, 4, 5, 5, 5, 8, 8, 11, 13, 16, 17, 17, 18, 20, 20, 20, 23, 25, 28, 28, 28, 31, 32, 36, 36, 36, 36, 44, 44, 47, 49, 56, 59, 64, 64, 73, 81};

        int result[] = new Solver().solve(arr);
        System.out.println("Result = " + Arrays.toString(result));
    }   
    
    static class Solver {
        final int[] EMPTY_ARRAY = new int[] {};

        int[] solve(int[] arr) {
            // Main result to hold
            int n = arr.length;
            int[] result = new int[n];

            Arrays.sort(arr);

            Map<Integer, List<Integer>> map = new TreeMap<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        if (isPerfectSquare(arr[i] + arr[j])) {
                            List<Integer> list = map.get(i);
                            if (list == null) list = new ArrayList<>();
                            list.add(j);
                            map.put(i, list);
                        }
                    }
                }
            }

            for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

            for (int i = 0; i < n; i++) {
                result[0] = arr[i];
                Set<Integer> visited = new HashSet<>();
                visited.add(i);
                if (fillArray(arr, map, result, i, 1, visited)) return result;
            }

            return EMPTY_ARRAY;
        }

        boolean fillArray(int[] ref, Map<Integer, List<Integer>> map, int[] res, int pos, int count, Set<Integer> visited) {
            if (count == ref.length) return true;
            boolean added = false;
            
            List<Integer> indexes = map.get(pos);
            if (indexes == null) return false;

            for (int index: indexes) {
                if (visited.contains(index)) continue;
                res[count] = ref[index];
                visited.add(index);
                added = true;
                if (!fillArray(ref, map, res, index, count + 1, visited)) {
                    added = false;
                    visited.remove(index);
                }
            }
            return added;
        }

        boolean isPerfectSquare(int x) {
            int y = (int) Math.sqrt(x);
            
            return y * y == x;
        }
    }
}