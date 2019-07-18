import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTrees {
    public static void inOrderTraversal(BinaryTree binaryTree) {
        if (binaryTree == null) return;
        inOrderTraversal(binaryTree.left);
        System.out.print(binaryTree.value + " ");
        inOrderTraversal(binaryTree.right);
    }

    public static void preOrderTraversal(BinaryTree binaryTree) {
        if (binaryTree == null) return;
        System.out.print(binaryTree.value + " ");
        preOrderTraversal(binaryTree.left);
        preOrderTraversal(binaryTree.right);
    }
    
    public static void postOrderTraversal(BinaryTree binaryTree) {
        if (binaryTree == null) return;
        postOrderTraversal(binaryTree.left);
        postOrderTraversal(binaryTree.right);
        System.out.print(binaryTree.value + " ");
    }

    public static void bfs(BinaryTree binaryTree) {
        ArrayDeque<BinaryTree> queue = new ArrayDeque<>();
        queue.add(binaryTree);

        while(queue.size() > 0) {
            BinaryTree bTree = queue.poll();
            System.out.print(bTree.value + " ");

            if (bTree.left != null) queue.add(bTree.left);
            if (bTree.right != null) queue.add(bTree.right);
        }
    }

    public static boolean search(BinaryTree binaryTree, Integer element) {
        if (binaryTree == null) return false;
        if (binaryTree.value == element) return true;
        if (binaryTree.value < element) return search(binaryTree.right, element);
        else return search(binaryTree.left, element);
    }

    public static boolean delete(BinaryTree binaryTree, Integer element) {
        if (binaryTree == null) return false;
        if (element < binaryTree.value) return delete(binaryTree.left, element);
        if (element > binaryTree.value) return delete(binaryTree.right, element);

        if (binaryTree.left == null && binaryTree.right == null) {
            System.out.println("Leaf");
            binaryTree.value = null;
            binaryTree = null;
            return true;
        }
        if (binaryTree.left == null) {
            binaryTree.value = binaryTree.right.value;
            binaryTree.right = null;
            return true;
        }
        if (binaryTree.right == null) {
            binaryTree.value = binaryTree.left.value;
            binaryTree.left = null;
            return true;
        }
        Integer max = maxValue(binaryTree.left);
        binaryTree.value = max;
        delete(binaryTree.left, max);
        return true;
    }

    public static Integer maxValue(BinaryTree binaryTree) {
        if (binaryTree == null) return null;
        if (binaryTree.right == null) return binaryTree.value;
        return maxValue(binaryTree.right);
    }
}