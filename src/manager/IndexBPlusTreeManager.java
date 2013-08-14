package manager;

import config.AppConfig;
import model.IndexBPlusTree;
import model.Node;
import model.Product;

public class IndexBPlusTreeManager {
    public void addProduct(IndexBPlusTree tree, Product product) {
        Node root = tree.getRoot();
        if (root.getNoKeys() == AppConfig.MAXKEYS) {
            Node newRoot = new Node();
            newRoot.setLeaf(false);
            newRoot.setChildNodes(new Node[]{root});
            splitNode(newRoot, 0, root);
            insertProductIntoNode(newRoot, product);
        } else {
            insertProductIntoNode(root, product);
        }
    }

    void splitNode(Node parentNode, int n, Node node) {
        Node newNode = new Node();
        newNode.setLeaf(node.isLeaf());
        int resetSize = AppConfig.MAXKEYS/2;
        newNode.setNoKeys(resetSize);
        for (int i = 0; i < resetSize; i++) {
            newNode.setIthKey(node.getIthKey(i + resetSize - 1), i);
            newNode.setIthProduct(node.getIthProduct(i + resetSize - 1), i);
        }
        if (!newNode.isLeaf()) {
            for (int i = 0; i < resetSize + 1; i++) {
                newNode.setIthChildNode(node.getIthChildNode(i + resetSize - 1), i);
            }
            for (int i = resetSize; i <= node.getNoKeys(); i++) {
                node.setIthChildNode(null, i);
            }
        } else {
            newNode.setNextNode(node.getNextNode());
            node.setNextNode(newNode);
        }
        for (int i = resetSize - 1; i < node.getNoKeys(); i++) {
            node.setIthKey(0, i);
            node.setIthProduct(null, i);
        }
        node.setNoKeys(resetSize - 1);

        for (int j = parentNode.getNoKeys(); j >= n + 1; j--) {
            parentNode.setIthChildNode(parentNode.getIthChildNode(j), j+1);
        }
        parentNode.setIthChildNode(newNode, n + 1);
        for (int j = parentNode.getNoKeys() - 1; j >= n; j--) {
            parentNode.setIthKey(parentNode.getIthKey(j), j+1);
            parentNode.setIthProduct(parentNode.getIthProduct(j), j+1);
        }
        parentNode.setIthKey(newNode.getIthKey(0), n);
        parentNode.setIthProduct(newNode.getIthProduct(0), n);
        parentNode.setNoKeys(parentNode.getNoKeys() + 1);
    }

    void insertProductIntoNode(Node node, Product product) {
        int key = product.getProductId();
        int i = node.getNoKeys() - 1;
        if (node.isLeaf()) {
            while (i >= 0 && key < node.getIthKey(i)) {
                node.setIthKey(node.getIthKey(i), i+1);
                node.setIthProduct(node.getIthProduct(i), i+1);
                i--;
            }
            i++;
            node.setIthKey(key, i);
            node.setIthProduct(product, i);
            node.setNoKeys(node.getNoKeys()+1);
        } else {
            while (i >= 0 && key < node.getIthKey(i)) {
                i--;
            }
            i++;
            if (node.getIthChildNode(i).getNoKeys() == (AppConfig.MAXKEYS)) {
                splitNode(node, i, node.getIthChildNode(i));
                if (key > node.getIthKey(i)) {
                    i++;
                }
            }
            insertProductIntoNode(node.getIthChildNode(i), product);
        }
    }
}