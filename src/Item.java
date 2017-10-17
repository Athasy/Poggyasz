
public class Item {
	private int width;
	private int height;
	
	public Item(int w, int h) {
		width = w;
		height = h;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void rotate() {
		int temp = width;
		width = height;
		height = temp;
	}
}
