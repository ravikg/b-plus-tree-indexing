package model;

public class IndexBPlusTree {
		
	private Node root;
	
	public IndexBPlusTree() {
		root = new Node();
		root.setLeaf(true);
	}

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
