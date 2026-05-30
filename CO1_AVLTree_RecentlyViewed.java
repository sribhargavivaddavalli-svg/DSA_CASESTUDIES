import java.util.*;

class AVLNode {
    int id;
    String item;
    int height;
    AVLNode left, right;

    AVLNode(int id, String item) {
        this.id = id;
        this.item = item;
        this.height = 1;
    }
}

public class CO1_AVLTree_RecentlyViewed {

    AVLNode root;

    int height(AVLNode n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(AVLNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    AVLNode insert(AVLNode node, int id, String item) {
        if (node == null)
            return new AVLNode(id, item);

        if (id < node.id)
            node.left = insert(node.left, id, item);
        else
            node.right = insert(node.right, id, item);

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && id < node.left.id)
            return rightRotate(node);

        if (balance < -1 && id > node.right.id)
            return leftRotate(node);

        if (balance > 1 && id > node.left.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && id < node.right.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void printDescending(AVLNode node) {
        if (node == null) return;
        printDescending(node.right);

        System.out.println("Item ID      : " + node.id);
        System.out.println("Item Name    : " + node.item);
        System.out.println("--------------------------------------");

        printDescending(node.left);
    }

    public static void main(String[] args) {

        CO1_AVLTree_RecentlyViewed tree = new CO1_AVLTree_RecentlyViewed();

        System.out.println("------ RECENTLY VIEWED ITEMS (AVL TREE) ------");

        tree.root = tree.insert(tree.root, 45, "Laptop");
        tree.root = tree.insert(tree.root, 60, "Headphones");
        tree.root = tree.insert(tree.root, 30, "Smartphone");
        tree.root = tree.insert(tree.root, 50, "Keyboard");
        tree.root = tree.insert(tree.root, 40, "Charger");

        tree.printDescending(tree.root);

        System.out.println("Total Viewed Items : 5");
    }
}
    