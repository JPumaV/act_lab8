package avltree;

public class AVLTree<T extends Comparable<T>> extends BSTree<T> {

    protected class NodeAVL extends Node {
        int bf; // Balance factor

        public NodeAVL(T data) {
            super(data);
            this.bf = 0;
        }

        @Override
        public String toString() {
            return data + "(bf=" + bf + ")";
        }
    }

    public AVLTree() {
        super();
    }

    public void insert(T data) {
        root = insertAVL((NodeAVL) root, data);
    }

    private NodeAVL insertAVL(NodeAVL node, T data) {
        if (node == null)
            return new NodeAVL(data);

        if (data.compareTo(node.data) < 0) {
            node.left = insertAVL((NodeAVL) node.left, data);
            node = rebalanceAfterInsertLeft(node);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insertAVL((NodeAVL) node.right, data);
            node = rebalanceAfterInsertRight(node);
        }
        return node;
    }

    private NodeAVL rebalanceAfterInsertLeft(NodeAVL node) {
        node.bf--;
        if (node.bf == -2)
            node = balanceToRight(node);
        return node;
    }

    private NodeAVL rebalanceAfterInsertRight(NodeAVL node) {
        node.bf++;
        if (node.bf == 2)
            node = balanceToLeft(node);
        return node;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL rightChild = (NodeAVL) node.right;
        if (rightChild.bf >= 0) {
            node.bf = 0;
            rightChild.bf = 0;
            node = rotateSL(node);
        } else {
            NodeAVL rightLeft = (NodeAVL) rightChild.left;
            switch (rightLeft.bf) {
                case -1:
                    node.bf = 0;
                    rightChild.bf = 1;
                    break;
                case 0:
                    node.bf = 0;
                    rightChild.bf = 0;
                    break;
                case 1:
                    node.bf = 1;
                    rightChild.bf = 0;
                    break;
            }
            rightLeft.bf = 0;
            node.right = rotateSR(rightChild);
            node = rotateSL(node);
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL leftChild = (NodeAVL) node.left;
        if (leftChild.bf <= 0) {
            node.bf = 0;
            leftChild.bf = 0;
            node = rotateSR(node);
        } else {
            NodeAVL leftRight = (NodeAVL) leftChild.right;
            switch (leftRight.bf) {
                case -1:
                    node.bf = 1;
                    leftChild.bf = 0;
                    break;
                case 0:
                    node.bf = 0;
                    leftChild.bf = 0;
                    break;
                case 1:
                    node.bf = 0;
                    leftChild.bf = -1;
                    break;
            }
            leftRight.bf = 0;
            node.left = rotateSL(leftChild);
            node = rotateSR(node);
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL newRoot = (NodeAVL) node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL newRoot = (NodeAVL) node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    public void inOrder() {
        inOrder((NodeAVL) root);
        System.out.println();
    }

    private void inOrder(NodeAVL node) {
        if (node != null) {
            inOrder((NodeAVL) node.left);
            System.out.print(node + " ");
            inOrder((NodeAVL) node.right);
        }
    }
}
