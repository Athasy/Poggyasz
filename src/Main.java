import java.util.List;

public class Main {

	public int[][] luggage;
	public List<Item> items;
	public int itemCount;
	public int lWidth;
	public int lHeight;
	
	
	public void main(){
		readItems();
		
		putItemsIntoLuggage();
		
		printLuggage();
	}
	
	// method of algorythm
	
	public void putItemsIntoLuggage() {
		
	}
	
	
	// IO hardcore shit
	
	public void readItems() {
		
	}
	
	public void printLuggage() {
		String output;
		for(int i = 0; i < lHeight; i++) {
			output = "";
			for(int j = 0; i < lWidth-2; j++) {
				output += luggage[i][j] + "\t";
			}
			output += luggage[i][lWidth-1] + "\n";
			System.out.println(output);
		}
	}
}
