import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    private TreeNode root;

    public List<Integer> onlyChildren() {
        ArrayList<Integer> onlyChildren = new ArrayList<>();

        traversePrivate(root, onlyChildren);
        return onlyChildren;
    }

    private void traversePrivate(TreeNode node, List<Integer> list) {
        if (node != null) {

            if (node.getLeftChild() != null) {
                if (node.getRightChild() == null) {
                    list.add(node.getLeftChild().getVal());
                } else if (node.getRightChild().getVal() == null) {
                    list.add(node.getLeftChild().getVal());
                }
            }

            if (node.getRightChild() != null) {
                if (node.getLeftChild() == null) {
                    list.add(node.getRightChild().getVal());
                } else if (node.getLeftChild().getVal() == null) {
                    list.add(node.getRightChild().getVal());
                }
            }

            traversePrivate(node.getLeftChild(), list);
            traversePrivate(node.getRightChild(), list);
        }
    }

    public void traverse(TreeNode node) {
        if (node != null) {
            if (node.getVal() != null) {
                traverse(node.getLeftChild());
                System.out.println(node + " - left: " + node.getLeftChild() + "- right: "
                        + node.getRightChild());
                traverse(node.getRightChild());
            }
        }
    }

    public static BinaryTree createBinaryTree(Integer[] vals) {
        BinaryTree binaryTree = new BinaryTree();

        for (Integer val : vals) {
            binaryTree.add(val);
        }

        return binaryTree;

    }

    private void add(Integer val) {
        if (root == null) {
            root = new TreeNode(val);
            return;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            TreeNode node = nodes.remove();

            if (node.getLeftChild() == null) {
                node.setLeftChild(new TreeNode(val));
                break;
            } else if (node.getLeftChild().getVal() != null) {
                nodes.add(node.getLeftChild());
            }

            if (node.getRightChild() == null) {
                node.setRightChild(new TreeNode(val));
                break;
            } else if (node.getRightChild().getVal() != null) {
                nodes.add(node.getRightChild());
            }
        }
    }

    public TreeNode getRoot() {
        return this.root;
    }
}
