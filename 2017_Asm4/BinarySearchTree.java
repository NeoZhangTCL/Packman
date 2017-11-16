
public class BinarySearchTree implements BinarySearchTreeADT {
	
	private BinaryNode root;

	public BinarySearchTree() {
		this.root = null;
	}
	
	@Override
	public BinaryNode getRoot() {
		return this.root;
	}
	
	public void setRoot(BinaryNode root) {
		this.root = root;
	}

	@Override
	public Pixel get(BinaryNode root, Location key) {
		BinaryNode node = getHelper(root, key);
		if (node == null) return null;
		return node.getData();
	}
	
	private BinaryNode getHelper(BinaryNode root, Location key) {
		if (root == null) return null;
		int compare = root.getData().getLocation().compareTo(key);
		if (compare == 0) {
			return root;		// root location equal to key
		} else if (compare == 1) {
			return getHelper(root.getLeft(), key);	// root location smaller than key
		} else {						
			return getHelper(root.getRight(), key);	// root location greater to key
		}
	}
	
	@SuppressWarnings("null")
	@Override
	public void put(BinaryNode root, Pixel data) throws DuplicatedKeyException {
		BinaryNode node = new BinaryNode(data);
		if (root == null) {
			this.setRoot(node);
			return;
		}
		int compare = root.getData().getLocation().compareTo(data.getLocation());
		boolean leaf = root.isLeaf(); 
		// root location equal to key
		if (compare == 0) {
			throw new DuplicatedKeyException("This pixel value exist in tree already");		
		} 
		// root location smaller than data
		else if (compare == 1) { 
			if (root.getLeft() == null) {
				root.setLeft(node);
				node.setParent(root);
			} else {
				put(root.getLeft(), data);					
			}
		} 
		// root location greater to key			
		else {	
			if (root.getRight() == null) {
				root.setRight(node);
				node.setParent(root);
			} else {
				put(root.getRight(), data);			
			}
		}	
	}

	@Override
	public void remove(BinaryNode root, Location key) throws InexistentKeyException {
		BinaryNode toRemove = getHelper(root, key);
		if (toRemove == null)
			throw new InexistentKeyException("the node with the key's location not exist");
		this.setRoot(removeHelper(root, key));
	}

	
	public BinaryNode removeHelper(BinaryNode root, Location key) {
	    if(root == null){
	        return null;
	    }
		int compare = root.getData().getLocation().compareTo(key);
		// root's location greater than key
	    if(compare == 1){	
	        root.setLeft(removeHelper(root.getLeft(), key));
	    }
		// root's location smaller than key
	    else if(compare == -1){
	        root.setRight(removeHelper(root.getRight(), key));
	    }
	    // root's location is key
	    else{
	        if(root.getLeft() == null){
	            return root.getRight();
	        }else if(root.getRight() == null){
	            return root.getLeft();
	        }
	        
	        BinaryNode minNode = findMin(root.getRight());
	        root.setData(minNode.getData());
	        root.setRight(removeHelper(root.getRight(), root.getData().getLocation()));
	    }
	    return root;
	}

	private BinaryNode findMin(BinaryNode node){
	    while(node.getLeft() != null){
	        node = node.getLeft();
	    }
	    return node;
	}
	
	@Override
	public Pixel successor(BinaryNode root, Location key) {
		BinaryNode node = successorHelper(root, key);
		if (node == null) return null;
		return node.getData();
	}
	
	private BinaryNode successorHelper(BinaryNode root, Location key) {
	    if (root == null) return null;
		int compare = root.getData().getLocation().compareTo(key);
		// root location smaller than or equal to data
	    if (compare <= 0) {
	    	return successorHelper(root.getRight(), key);
	    } else {
	    	BinaryNode left = successorHelper(root.getLeft(), key);
	    	return (left != null) ? left : root;
	    }
	}

	@Override
	public Pixel predecessor(BinaryNode root, Location key) {
		BinaryNode node = predecessorHelper(root, key);
		if (node == null) return null;	    
		return node.getData();
	}
	
	private BinaryNode predecessorHelper(BinaryNode root, Location key) {
	    if (root == null) return null;
		int compare = root.getData().getLocation().compareTo(key);
		// root location greater than or equal to key
	    if (compare >= 0) {
	    	return predecessorHelper(root.getLeft(), key);
	    } else {
	    	BinaryNode right = predecessorHelper(root.getRight(), key);
	    	return (right != null) ? right : root;
	    }
	}

	@Override
	public Pixel smallest(BinaryNode root) throws EmptyTreeException {
		if (root == null) 
			throw new EmptyTreeException("the tree root cannot be empty");
		while (!root.isLeaf()) {
			root = root.getLeft();
		}
		return root.getData();
	}

	@Override
	public Pixel largest(BinaryNode root) throws EmptyTreeException {
		if (root == null) 
			throw new EmptyTreeException("the tree root cannot be empty");
		while (!root.isLeaf()) {
			root = root.getRight();
		}
		return root.getData();
	}

}
