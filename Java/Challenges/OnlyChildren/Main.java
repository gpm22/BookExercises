import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting thet test...");

        // String stringVals = "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14";
        String stringVals = "0 1 2 null 4 5 6 7 8 9 10 11 null 13 14";
        System.out.println("Values: \n" + stringVals);

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

        System.out.println("Only Children: \n" + binaryTree.onlyChildren());

        System.out.println("DFS recursive traverse:\n");

        binaryTree.traverseDFSRecursive(binaryTree.getRoot());

        System.out.println("\nDFS iterative traverse:\n");

        binaryTree.traverseDFSIterative();

        System.out.println("\nBFS iterative traverse:\n");
        binaryTree.traverseBFSIterative();

        System.out.println("\nBFS recursive traverse:\n");
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(binaryTree.getRoot());
        binaryTree.traverseBFSRecursive(nodes);

    }
}
