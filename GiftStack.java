import java.util.*;

public class GiftStack {

    public static void main(String[] args) {
        //int[][] b = ;
        String s = 

        System.out.println(giftStacking(b));
    }

    static int giftStacking(int[][] boxes) {
        List<Box> boxList = new ArrayList<>();
        for (int[] box: boxes) {
            boxList.add(new Box(box[0], box[1]));
        }
        
        Collections.sort(boxList);
        System.out.println(boxList);
        
        return 0;
    }
    
    static class Box implements Comparable<Box> {
        int strength, weight;
        
        Box (int strength, int weight) {
            this.strength = strength;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Box other) {
            System.out.println(this + " " + other);
            return this.strength - other.weight;
        }
        
        @Override
        public String toString() {
            return "[" + strength + ", " + weight + "]";
        }
    }
} 