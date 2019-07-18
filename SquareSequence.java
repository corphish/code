import java.util.*;

public class SquareSequence {
    public static void main(String args[]) {
        for (int i = 51; i <= 100; i++) {
            long t = System.currentTimeMillis();
            System.out.println(i + " -> " + squareSequence(i) + " [Took " + (System.currentTimeMillis() - t) + "ms.]");
        }
    }

    static List<Integer> squareSequence(int n) {
        Map<Integer, Set<Integer>> map = new TreeMap<>();
        
        for (int i = 1; i <= n; i++) {
            Set<Integer> set = new TreeSet<>();
            
            for (int j = 1; j <= n; j++) {
                if (i != j && isPerfectSquare(i + j)) set.add(j);
            }
            
            map.put(i, set);
        }
        
        for (int x = 1; x <= n; x++)
            if (x <= n) {
                List<Integer> res = buildPath(map, x, n, new HashSet<Integer>(), new ArrayList<Integer>());
                if (res != null) return res;
            }
        
        return new ArrayList<Integer>();
    }

    static List<Integer> buildPath(Map<Integer, Set<Integer>> map, int last, int n, Set<Integer> included, List<Integer> intermediate) {
        if (included.contains(last)) return null;
        
        Set<Integer> nodes = map.get(last);
        // if (nodes.size() == 0) return false;
        
        intermediate.add(last);
        included.add(last);
        
        if (intermediate.size() == n) {
            return intermediate;
        }
        
        List<Integer> k = null;
        
        for (int x: nodes) {
            k = buildPath(map, x, n, included, intermediate);
            if (k != null) return intermediate;
        }
        
        intermediate.remove(Integer.valueOf(last));
        included.remove(last);
        
        return null;
    }

    static boolean isPerfectSquare(int n) {
        int x = (int) Math.sqrt(n);
        
        return x * x == n;
    }

}