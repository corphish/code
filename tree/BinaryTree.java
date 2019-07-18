
public class BinaryTree {
    Integer value;
    BinaryTree left;
    BinaryTree right;

    public static BinaryTree createFromArray(Integer[] items) {
        BinaryTree binaryTree = new BinaryTree();

        for (Integer item: items) insertInTree(binaryTree, item);

        return binaryTree;
    }

    private static void insertInTree(BinaryTree binaryTree, Integer item) {
        if (binaryTree.value == null) {
            binaryTree.value = item;
            return;
        }
        if (item < binaryTree.value) {
            if (binaryTree.left == null) binaryTree.left = new BinaryTree();
            insertInTree(binaryTree.left, item);
        } else {
            if (binaryTree.right == null) binaryTree.right = new BinaryTree();
            insertInTree(binaryTree.right, item);
        }
    }
}