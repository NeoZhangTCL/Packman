 /* ============================================
  * Author: Tuochaolong Zhang (250787957)
  * Used for Assignment 4 of COMPSCI 2210 a
  * This program is a making a binary search node for binary search tree
  *  Methods: childrenNum; getData, getLeft, getRight ,getParent ,setData ,setLeft ,setRight ,setParent
= ============================================*/
public class BSTNode{

	//CONSTRUCTOR
      private DictEntry data;
      private BSTNode left, right, parent;
      
      //FEILD
      public BSTNode (DictEntry element ){
        this.data = element;
         this.left = null;
         this.right = null;
         this.parent = null;
      }

      //get data
	public DictEntry getData() {
		return data;
	}

	//set data
	public void setData(DictEntry data) {
		this.data = data;
	}

	//get right subtree node
	public BSTNode getRight() {
		return right;
	}

	//set right subtree node
	public void setRight(BSTNode right) {
		this.right = right;
	}

	//get left subtree node
	public BSTNode getLeft() {
		return left;
	}
	
	//set left subtree node
	public void setLeft(BSTNode left) {
		this.left = left;
	}

	//get parent subtree node
	public BSTNode getParent() {
		return parent;
	}

	//set parent subtree node
	public void setParent(BSTNode parent) {
		this.parent = parent;
	}
	
	//count the child num
	public int childrenNum(){
		if (this.left == null && this.right == null){
			return 0;
		}
		else if (this.left != null && this.right != null){
			return 2;
		}
		else return 1;
	}
      
   }