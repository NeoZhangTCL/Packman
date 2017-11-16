
public class Figure implements FigureADT {
	
	private int id;
	private int width;
	private int height;
	private int type;
	private Position pos = new Position(width,height); 
	private BinarySearchTree tree = new BinarySearchTree();
	
	public Figure (int id, int width, int height, int type, Position pos){
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int width) {
		 this.width = width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int height) {
		 this.height = height;
	}
	
	public int getType() {
		return type;
	}

	//Sets type of figure to the specified value.
	public void setType(int type) {
		this.type = type;
	}

    public Position getOffset(){
    	return this.pos;    	
    }
    public void setOffset(Position value){
    	this.pos = value;
    }
    
    public void addPixel(int x, int y, int rgb) throws BSTException{
    	Position p = new Position(x,y);
    	DictEntry pixel = new DictEntry(p, rgb);
    	
    	try{
    		this.tree.insert(pixel);
    	}catch(BSTException e){
    		throw new BSTException("pixel already exist in the tree");
    	}
    }
    public boolean intersects(Figure fig){
    	int top = this.getOffset().getY();
    	int bottom = this.getOffset().getY() + this.getHeight();
    	int left = this.getOffset().getX();
    	int right = this.getOffset().getX() + this.getWidth();
    	int figTop = fig.getOffset().getY();
    	int figBottom = fig.getOffset().getY() + fig.getHeight();
    	int figLeft = fig.getOffset().getX();
    	int figRight = fig.getOffset().getX() + fig.getWidth();
    	
    	// if X makes it impossible intersects
    	if (left > figRight || right < figLeft){
    		return false;
    	}
    	else{
    		if(top > figBottom || bottom < figTop ){
    			return false;
    		}
    		else return true;
    	}
    	

    	
    }





	
}
