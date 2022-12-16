package letsgo;

enum CellState {B, W};

public class Piece {
	
	private CellState type;
	private int total_distace = 0;
	
	public void addDistance(int x_start, int y_start, int x_end, int y_end){
		int temp=0;
		total_distace += temp;
	}
		
	public CellState getType() {
		return type;
	}
	public void setType(CellState type) {
		this.type = type;
	}
	public int getTotal_distace() {
		return total_distace;
	}
	public void setTotal_distace(int total_distace) {
		this.total_distace = total_distace;
	}
			
}
