
public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	int compareTo(Position p){
		if (this.y < p.getY()){
			return -1;
		}
		else if (this.y == p.getY()){
			if(this.x < p.getX()){
				return -1;
			}
			else if (this.x == p.getX()){
				return 0;
			}
			else return 1;
		}
		else return 1;
	}
	
}
