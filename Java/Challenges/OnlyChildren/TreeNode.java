public class TreeNode {
    private TreeNode leftChild;
    private TreeNode rightChild;
    private Integer val;

    public TreeNode(Integer val) {
        this.val = val;
        this.leftChild = null;
        this.rightChild = null;
    }

    /**
     * @return TreeNode return the leftChild
     */
    public TreeNode getLeftChild() {
        return leftChild;
    }

    /**
     * @param leftChild the leftChild to set
     */
    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return TreeNode return the rightChild
     */
    public TreeNode getRightChild() {
        return rightChild;
    }

    /**
     * @param rightChild the rightChild to set
     */
    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * @return int return the val
     */
    public Integer getVal() {
        return val;
    }

    /**
     * @param val the val to set
     */
    public void setVal(Integer val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "" + this.val;
    }

}