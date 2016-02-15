 /* ============================================
  * Author: Tuochaolong Zhang (250787957)
  * Used for Assignment 4 of COMPSCI 2210 a
  * This program is a making a binary search tree made by BSTNodes
  *  Methods: find; insert largest, smallest, predecessor, remove, smallest, successor
= ============================================*/
public class BinarySearchTree implements BinarySearchTreeADT{
	
	//CONSTRUCTOR
	protected BSTNode root;
	
	//FEILD
	public BinarySearchTree(){
		this.root = null;
	}
	
	//METHODS
	
	 /* ============================================
	  *IN: Position key
	  *OUT: Returns the DictEntry storing the given key, if the key is stored in the tree. Returns null otherwise.
	  * use find(node,Position) to get the node with key to find, if it is null return null, else return the node's dictEntry 
	= ============================================*/
	public DictEntry find(Position key){
		if  (find(this.root, key) == null){
			return null;
		}
		else
			return find(this.root, key).getData();
	}
	
	 /* ============================================
	  *IN: BSTNode root, Position key
	  *OUT: Returns the Node storing the given key, if the key is stored in the tree. Returns null otherwise.
	= ============================================*/
	private BSTNode find(BSTNode root,  Position key){
		//set up the node for searching
		BSTNode sreaching = this.root;
		//do loop until it reach the key's node 
		while(true){
			//if root is null return null
			if (sreaching == null){
				return null;
			}
			//if searching is the node we want return searching
			if(sreaching.getData().getPosition().compareTo(key)==0){
				return sreaching;
			}
			//if searching's key < key, make searching to its right node
			else if (sreaching.getData().getPosition().compareTo(key)==-1){
				sreaching = sreaching.getRight();	
			}
			//if searching's key > key, make searching to its left node
			else{
				sreaching = sreaching.getLeft();
			}
		}
	}
	
	 /* ============================================
	  *IN: DictEntry data
	  *OUT: Inserts the given data in the tree if no data item with the same key is already there. If a node already stores the same key, 
	  *the algorithm throws a BSTException.
	= ============================================*/
	public void insert (DictEntry data) throws BSTException{
		//set up the node for searching
		BSTNode searching = this.root;
		//if the tree is empty
		if(searching == null){
			root = new BSTNode(data);
		}
		else{
			//check if the data in node has same key as finding
			while(true){
				if (searching.getData().getPosition().compareTo(data.getPosition()) == 0){
					throw new BSTException("key already exist.");
				}
				//else if there is no left child and sreaching.getPosition is larger than data
				else if(searching.getLeft() == null && searching.getData().getPosition().compareTo(data.getPosition()) == 1){
					BSTNode inserted = new BSTNode(data);
					inserted.setParent(searching);
					searching.setLeft(inserted);
					break;
				}
				//else if there is no right child and sreaching.getPosition is smaller than data			
				else if(searching.getRight() == null && searching.getData().getPosition().compareTo(data.getPosition()) == -1){
					BSTNode inserted = new BSTNode(data);
					inserted.setParent(searching);
					searching.setRight(inserted);
					break;
				}
				//if this data's larger and searching has right child, so shift searching to its right child
				else if(searching.getRight() != null &&searching.getData().getPosition().compareTo(data.getPosition()) == -1){
					searching = searching.getRight();
				}
				//if this data's smaller and searching has left child, so shift searching to its left child
				else if (searching.getLeft() != null &&searching.getData().getPosition().compareTo(data.getPosition()) == 1){
					searching = searching.getLeft();
				}
				else
					System.out.println("error");
			}
		}
	}

	 /* ============================================
	  *IN: Position key
	  *OUT: Removes the data item with the given key, if the key is stored in the tree. Throws a BSTException otherwise.
	  *THROWS: BSTException if target doesn't exist in this tree
	= ============================================*/
	public void remove (Position key) throws BSTException{
		//get the target node to remove
		BSTNode target = find(root,  key);
		//if target doesn't exist in this tree
		if (target == null){
			throw new BSTException("The key doesn't exist.");
		}
		else{
			//there is no child
			if(target.childrenNum()==0){
				//target is a single root
				if (target == root){
					root = null;
				}
				//if target is not root
				else{
					if(target.getParent().getLeft() == target){ // target in left branch
						target.getParent().setLeft(null);
						target.setParent(null);
						target = null;
					}
					else{ // target in right branch
						target.getParent().setRight(null);
						target.setParent(null);
						target = null;
					}
				}
			}
			//there is one child
			else if (target.childrenNum()==1){
				//if the node is root
				if (target == root){
					//leftwards
					if (target.getLeft() != null){
						this.root = target.getLeft();
						target.setLeft(null);
						root.setParent(null);
						target = null;
					}
					//rightwards
					else{
						this.root = target.getRight();
						target.setRight(null);
						root.setParent(null);
						target = null;
					}
				}
				//if the node is not root//////////////////PROBLEM//////////
				else{
					if (target.getParent().getRight() == target){ //parent left
						if (target.getLeft() != null){ //child left
							target.getParent().setRight(target.getLeft());
							target.getLeft().setParent(target.getParent());
							target.setLeft(null);
							target.setParent(null);
							target = null;
						}
						else{ //child right
							target.getParent().setRight(target.getRight());
							target.getRight().setParent(target.getParent());
							target.setParent(null);
							target.setRight(null);
							target = null;
						}
					}
					else{ // parent right
						if (target.getLeft() != null){//child left
							target.getParent().setLeft(target.getLeft());
							target.getLeft().setParent(target.getParent());
							target.setParent(null);
							target.setLeft(null);
							target = null;
						}
						else { //left child
							target.getParent().setLeft(target.getRight());
							target.getRight().setParent(target.getParent());
							target.setRight(null);
							target.setParent(null);
							target = null;							
						}
					}
				}
			}
			// there are two children
			else{
				//if the node is a root
				if (target == root){
					//find the smallest
					BSTNode smallest = smallest(target.getRight());
					//connect smallest's parent and child
					if (smallest.getRight() != null){
						smallest.getRight().setParent(smallest.getParent());	//if right child exist
					}
					if (smallest != target.getRight()){
						smallest.getParent().setLeft(smallest.getRight());
					}
					else{
						smallest.getParent().setRight(smallest.getRight());
					}
					//set smallest to target
					smallest.setRight(null);
					smallest.setParent(null);
					root.setData(smallest.getData());
				}
				//if the node is not a root
				else{
					//find the smallest
					BSTNode smallest = smallest(target.getRight());
//					System.out.println(smallest.getData().getPosition().getX()+ " " +smallest.getData().getPosition().getY()+ " " +smallest.getRight().getData().getPosition().getX()+ " " +smallest.getRight().getData().getPosition().getY());
					//connect smallest's parent and child
					if (smallest.getRight() != null){
						smallest.getRight().setParent(smallest.getParent());	//if right child exist
					}
					if (smallest != target.getRight()){
						smallest.getParent().setLeft(smallest.getRight());
					}
					else{
						smallest.getParent().setRight(smallest.getRight());
					}
//					System.out.println(smallest + " "+smallest.getParent()+" "+smallest.getRight().getParent()+" "+smallest.getRight()+" "+smallest.getParent().getLeft()+" "+target.getLeft());
					//set smallest to target
					smallest.setRight(null); 
					smallest.setParent(null);
					target.setData(smallest.getData());
//					System.out.println(smallest.getData() + " "+target.getData()+" "+smallest.getParent()+" "+smallest.getRight()+" "+target.getRight()+" "+target.getLeft());
					smallest = null;
				}	
			}
		}
	}

	 /* ============================================
	  *IN: Position key
	  *OUT:Returns the DictEntry with the smallest key
larger than the given one (note that the tree does not need to store a node with the given key). Returnsnull if the given key has no successor.
	= ============================================*/
	public DictEntry successor(Position key){
		return successor(this.root, key).getData();
	}
	
	 /* ============================================
	  *IN: BSTNode root, Position key
	  *OUT: Returns the BSTNode with the smallest key larger than the given one (note that the tree does not need to store a node with the given key). Returns null if the given key has no successor.
	= ============================================*/
	public BSTNode successor(BSTNode root, Position key){
		// get the target to find successor
		BSTNode target = find( this.root, key);
		
		//if target has right subtree, find smallest from it
		if (target == null){
			return null;
		}else if (target.getRight() != null){
			return smallest(target.getRight());
		}
		//going to parents until the parent is bigger than target or reach root
		else {
			while (target.getParent().getData().getPosition().compareTo(key) == -1){
				if (target.getParent() == null)
					return null;
				else
					target = target.getParent();
			}
			return target.getParent();
		}
	}
	
	 /* ============================================
	  *IN: Position key
	  *OUT: Returns the DictEntry with the largest key smaller than the given one (note that the tree does not need to store a node with the given key). Returns null if the given key has no predecessor.
	= ============================================*/
	public DictEntry predecessor (Position key){
		return predecessor(this.root, key).getData();
	}
	
	 /* ============================================
	  *IN: BSTNode root, Position key
	  *OUT: Returns the BSTNode with the largest key smaller than the given one (note that the tree does not need to store a node with the given key). Returns null if the given key has no predecessor.
	= ============================================*/	
	public BSTNode predecessor (BSTNode root, Position key){
		// get the target to find successor
		BSTNode target = find( this.root, key);
		if (target == null){
			return null;
		}
		//return to its left subtree's largest node
		else if (target.getLeft() != null){
			return largest(target.getLeft());
		}
		else {
			//going to parents until the parent is bigger than target or reach root
			while (target.getParent().getData().getPosition().compareTo(key) == 1){
				if (target.getParent() == null)
					return null;
				else
					target = target.getParent();
			}
			return target.getParent();
		}
	}

	 /* ============================================
	  *IN: BSTNode root
	  *OUT:Returns the DictEntry with the smallest key. Returns null if the tree does not contain any data.
	= ============================================*/		
	public BSTNode smallest(BSTNode node){
		// if there is no left subtree, return null
		if(node.getLeft() == null){
			return node;
		}
		//keep going left until it reach the left
		else{
			while (node.getLeft() != null){
				node = node.getLeft();
			}
			//return the leaff
			return node;
		}
	}

	 /* ============================================
	  *IN: BSTNode root
	  *OUT:Returns the DictEntry with the largest key. Returns null if the tree does not contain any data.
	= ============================================*/		
	public BSTNode largest(BSTNode node){
		// if there is no left subtree, return null
		if(node.getRight() == null){
			return node;
		}
		//keep going left until it reach the left
		else{
			while (node.getRight() != null){
				node = node.getRight();
			}
			//return the leaff
			return node;
			}
		}
	}

	


