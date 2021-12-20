import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    private TreeNode root;

    public List<Integer> onlyChildren() {
        ArrayList<Integer> onlyChildren = new ArrayList<>();

        traverseDFSPrivate(root, onlyChildren);
        return onlyChildren;
    }

    private void traverseDFSPrivate(TreeNode node, List<Integer> list) {
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

            traverseDFSPrivate(node.getLeftChild(), list);
            traverseDFSPrivate(node.getRightChild(), list);
        }
    }

    public void traverseDFSRecursive(TreeNode node) {
        if (node != null) {
            if (node.getVal() != null) {
                System.out.println(node + " - left: " + node.getLeftChild() + "- right: "
                        + node.getRightChild());
                traverseDFSRecursive(node.getLeftChild());
                traverseDFSRecursive(node.getRightChild());
            }
        }
    }

    public void traverseDFSIterative() {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.addLast(root);

        while (!nodes.isEmpty()) {

            TreeNode node = nodes.removeLast();

            System.out.println(node + " - left: " + node.getLeftChild() + "- right: "
                    + node.getRightChild());

            if (node.getRightChild() != null) {
                if (node.getRightChild().getVal() != null) {
                    nodes.add(node.getRightChild());
                }
            }

            if (node.getLeftChild() != null) {
                if (node.getLeftChild().getVal() != null) {
                    nodes.add(node.getLeftChild());
                }
            }
        }
    }

    public void traverseBFSRecursive(Queue<TreeNode> nodes) {

        if (nodes.isEmpty()) {
            return;
        }

        TreeNode node = nodes.remove();

        if (node != null) {
            if (node.getVal() != null) {
                System.out.println(node + " - left: " + node.getLeftChild() + "- right: "
                        + node.getRightChild());

                if (node.getLeftChild() != null) {
                    if (node.getLeftChild().getVal() != null) {
                        nodes.add(node.getLeftChild());
                    }
                }

                if (node.getRightChild() != null) {
                    if (node.getRightChild().getVal() != null) {
                        nodes.add(node.getRightChild());
                    }
                }

                traverseBFSRecursive(nodes);
            }
        }
    }

    public void traverseBFSIterative() {
        if (root == null) {
            return;
        }

        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            TreeNode node = nodes.remove();

            System.out.println(node + " - left: " + node.getLeftChild() + "- right: "
                    + node.getRightChild());

            if (node.getLeftChild() != null) {
                if (node.getLeftChild().getVal() != null) {
                    nodes.add(node.getLeftChild());
                }
            }

            if (node.getRightChild() != null) {
                if (node.getRightChild().getVal() != null) {
                    nodes.add(node.getRightChild());
                }
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
