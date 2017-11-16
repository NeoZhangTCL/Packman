
public class BinaryNode {
	
	private Pixel value;
	private BinaryNode left;
	private BinaryNode right;
	private BinaryNode parent;
	
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	
	public BinaryNode(Pixel value) {
		this.value = value;
	} 
	
	public BinaryNode getParent() {
		return this.parent;
	}
	
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}
	
	public void setLeft (BinaryNode p) {
		this.left = p;
	}
	
	public void setRight (BinaryNode p) {
		this.right = p;
	}
	
	public void setData (Pixel value) {
		this.value = value;
	}
	
	public boolean isLeaf() {
		return (this.left == null && this.right == null);
	}
	
	public boolean isRoot() {
		return this.getParent() == null;
	}
	
	public boolean branch() {
		// true for left 
		// false for right
		return this.getParent().getLeft() == this ? true : false;
	}
	
	public Pixel getData() {
		return this.value;
	}
	
	public BinaryNode getLeft() {
		return this.left;
	}
	
	public BinaryNode getRight() {
		return this.right;
	}
}
