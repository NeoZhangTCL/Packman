
public class Location {

	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int xCoord() {
		return this.x;
	}
	
	public int yCoord() {
		return this.y;
	}
	
	public int compareTo (Location p) {
		if (this.x < p.x) {
			return -1;
		} 
		else if (this.x > p.x){
			return 1;
		} 
		else {
			if (this.y < p.y) {
				return -1;
			} 
			else if (this.y > p.y){
				return 1;
			} 
			else {
				return 0;
			}
		}
	}
}
