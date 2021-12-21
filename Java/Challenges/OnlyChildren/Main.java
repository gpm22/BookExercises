import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the values to create the binary tree:");
        String stringVals = scanner.nextLine();
        scanner.close();

        String[] stringArrayVals = stringVals.split(" ");

        Integer[] testVals = new Integer[stringArrayVals.length];

        for (int i = 0; i < stringArrayVals.length; i++) {
            try {
                testVals[i] = Integer.parseInt(stringArrayVals[i]);
            } catch (Exception e) {
                testVals[i] = null;
            }

        }

        BinaryTree binaryTree = BinaryTree.createBinaryTree(testVals);

        System.out.println("\nBinary Tree Created...\nTraversing the tree with different methods:");

        System.out.println("\nDFS recursive traverse:\n");

        binaryTree.traverseDFSRecursive(binaryTree.getRoot());

        System.out.println("\nDFS iterative traverse:\n");

        binaryTree.traverseDFSIterative();

        System.out.println("\nBFS iterative traverse:\n");
        binaryTree.traverseBFSIterative();

        System.out.println("\nBFS recursive traverse:\n");
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(binaryTree.getRoot());
        binaryTree.traverseBFSRecursive(nodes);

        System.out.println("\nOnly Children: \n" + binaryTree.onlyChildren());

    }
}
