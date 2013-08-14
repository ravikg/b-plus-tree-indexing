package model;

import config.AppConfig;

public class Node {

	private int noKeys = 0;
	private int[] pidKeys = new int[AppConfig.MAXKEYS];
	private Product[] products = new Product[AppConfig.MAXKEYS];
	private Node[] childNodes = new Node[AppConfig.MAXKEYS + 1];
	private boolean isLeaf;
    private Node nextNode;

    public int getIthKey(int i) {
        return this.pidKeys[i];
    }

    public void setIthKey(int pid, int i){
        this.pidKeys[i] = pid;
    }

    public Product getIthProduct(int i) {
        return this.products[i];
    }

    public void setIthProduct(Product product, int i) {
        this.products[i] = product;
    }

    public Node getIthChildNode(int i) {
        return this.childNodes[i];
    }

    public void setIthChildNode(Node node, int i) {
        this.childNodes[i] = node;
    }

    public int getNoKeys() {
        return noKeys;
    }

    public void setNoKeys(int noKeys) {
        this.noKeys = noKeys;
    }

    public int[] getPidKeys() {
        return pidKeys;
    }

    public void setPidKeys(int[] pidKeys) {
        this.pidKeys = pidKeys;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Node[] getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Node[] childNodes) {
        this.childNodes = childNodes;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}