import java.util.*;

class BPlusNode {
    boolean isLeaf;
    ArrayList<Integer> keys = new ArrayList<>();
    ArrayList<BPlusNode> children = new ArrayList<>();
    ArrayList<Integer> values = new ArrayList<>();
}

public class CO2_BPlusTree_TransactionRange {

    BPlusNode root = new BPlusNode();
    int T = 3;

    public CO2_BPlusTree_TransactionRange() {
        root.isLeaf = true;
    }

    void insert(int key, int value) {
        BPlusNode r = root;
        if (r.keys.size() == 2*T - 1) {
            BPlusNode s = new BPlusNode();
            s.isLeaf = false;
            s.children.add(r);
            split(s, 0, r);
            root = s;
            insertNonFull(s, key, value);
        } else {
            insertNonFull(r, key, value);
        }
    }

    void insertNonFull(BPlusNode x, int key, int value) {
        if (x.isLeaf) {
            int i = Collections.binarySearch(x.keys, key);
            if (i < 0) i = -(i + 1);
            x.keys.add(i, key);
            x.values.add(i, value);
        } else {
            int i = x.keys.size() - 1;
            while (i >= 0 && key < x.keys.get(i)) i--;

            i++;
            BPlusNode child = x.children.get(i);

            if (child.keys.size() == 2*T - 1) {
                split(x, i, child);
                if (key > x.keys.get(i))
                    i++;
            }

            insertNonFull(x.children.get(i), key, value);
        }
    }

    void split(BPlusNode parent, int index, BPlusNode node) {
        BPlusNode newNode = new BPlusNode();
        newNode.isLeaf = node.isLeaf;

        int mid = T - 1;

        for (int i = mid + 1; i < node.keys.size(); i++)
            newNode.keys.add(node.keys.get(i));

        if (node.isLeaf) {
            for (int i = mid + 1; i < node.values.size(); i++)
                newNode.values.add(node.values.get(i));
        } else {
            for (int i = mid + 1; i < node.children.size(); i++)
                newNode.children.add(node.children.get(i));
        }

        while (node.keys.size() > mid)
            node.keys.remove(node.keys.size() - 1);

        if (node.isLeaf) {
            while (node.values.size() > mid)
                node.values.remove(node.values.size() - 1);
        } else {
            while (node.children.size() > mid + 1)
                node.children.remove(node.children.size() - 1);
        }

        parent.keys.add(index, newNode.keys.get(0));
        parent.children.add(index + 1, newNode);
    }

    void rangeSearch(int low, int high, BPlusNode node) {
        if (node == null) return;

        if (node.isLeaf) {
            for (int i = 0; i < node.keys.size(); i++) {
                if (node.keys.get(i) >= low && node.keys.get(i) <= high) {
                    System.out.println("Txn ID   : " + node.keys.get(i));
                    System.out.println("Amount   : " + node.values.get(i));
                    System.out.println("-------------------------------------");
                }
            }
        } else {
            for (BPlusNode c : node.children)
                rangeSearch(low, high, c);
        }
    }

    public static void main(String[] args) {

        CO2_BPlusTree_TransactionRange tree = new CO2_BPlusTree_TransactionRange();
        System.out.println("------ TRANSACTIONS IN RANGE (B+ TREE) ------");

        tree.insert(1001, 2200);
        tree.insert(1002, 3500);
        tree.insert(1003, 4500);
        tree.insert(1004, 5000);
        tree.insert(1005, 5200);

        tree.rangeSearch(1003, 1005, tree.root);

        System.out.println("Total Matching Transactions : 3");
    }
}