package avltree;

public class TestAVL {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();

        int[] elementos = {
                30, 20, 10,
                40, 50,
                45, 60, 70,
                65,
                35, 5,
                33
        };

        for (int elem : elementos) {
            System.out.println("Insertando: " + elem);
            avl.insert(elem);
            avl.inOrder();
        }
    }
}