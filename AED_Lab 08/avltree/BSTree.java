package avltree;

public class BSTree<T extends Comparable<T>> {
    protected class Node {
        protected T data;
        protected Node left, right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    protected Node root;

    public BSTree() {
        this.root = null;
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    protected Node insert(Node node, T data) {
        if (node == null) return new Node(data);
        if (data.compareTo(node.data) < 0)
            node.left = insert(node.left, data);
        else if (data.compareTo(node.data) > 0)
            node.right = insert(node.right, data);
        return node;
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node + " ");
            inOrder(node.right);
        }
    }
}