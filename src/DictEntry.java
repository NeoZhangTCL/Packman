
public class DictEntry{
	
	private Position p;
	private int color;
	
	public DictEntry(Position p, int color){
		this.p = p;
		this.color = color;
	}
	
	public Position getPosition(){
		return p;
	}
	
	public int getColor(){
		return color;
	}
	
	
}