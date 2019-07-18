public class Driver {
    public static void main(String args[]) {
        BinaryTree binaryTree = BinaryTree.createFromArray(new Integer[] {6, 3, 9, 4, 7, 1, 5, 2, 8});
        BinaryTrees.delete(binaryTree, 5);
        System.out.print("In-order - ");
        BinaryTrees.inOrderTraversal(binaryTree);
        System.out.print("\nPre-order - ");
        BinaryTrees.preOrderTraversal(binaryTree);
        System.out.print("\nPost-order - ");
        BinaryTrees.postOrderTraversal(binaryTree);
        System.out.print("\nBFS - ");
        BinaryTrees.bfs(binaryTree);

        System.out.println("\n2 is present in tree - " + BinaryTrees.search(binaryTree, 2));
        System.out.println("0 is present in tree - " + BinaryTrees.search(binaryTree, 0));
        System.out.println("3 is present in tree - " + BinaryTrees.search(binaryTree, 3));
        System.out.println("5 is present in tree - " + BinaryTrees.search(binaryTree, 5));
        System.out.println("-1 is present in tree - " + BinaryTrees.search(binaryTree, -1));
    }
}