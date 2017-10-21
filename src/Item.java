
public class Item {
	private int width;
	private int height;
	private int area;
	private int id;
	
	public Item(int h, int w, int i) {
		width = w;
		height = h;
		id = i;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getArea() {
		return area;
	}
	
	public int getId() {
		return id;
	}
	
	public void calcArea() {
		area = width * height;
	}
	
	public void rotate() {
		int temp = width;
		width = height;
		height = temp;
	}
}
