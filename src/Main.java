import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	/*
10	6
14
6	1
2	3
4	2
2	2
6	2
2	2
3	1
1	2
3	1
3	2
1	3
1	1
1	1
1	1
	 */

	public static int[][] luggage;
	public static List<Item> items;
	public static int itemCount;
	public static int lWidth;
	public static int lHeight;
	private static Scanner sc;
	
	
	public static void main(String[] args){
		readItems();
		
		quickSort(0, itemCount-1);
		
		printItems();
		
		putItemsIntoLuggage();
		
		printLuggage();
	}
	
	// method of algorithm
	
	public static void putItemsIntoLuggage() {
		int at = itemCount-1;
		boolean done = false;
		
		while(at >= 0) {
			Item insertingItem = items.get(at);
			done = false;
			System.out.println("at:" + at + " itemID:" + insertingItem.getId());
			
			for(int y = 0; y < lHeight; y++) {
				for(int x = 0; x < lWidth; x++) {
					if(luggage[y][x] == 0 && !done) {
						if(fitsIntoLuggage(y, x, insertingItem.getHeight(), insertingItem.getWidth())) {
							putIntoLuggage(y, x, insertingItem);
							done = true;
						}
						else {
							insertingItem.rotate();
							if(fitsIntoLuggage(y,  x, insertingItem.getHeight(), insertingItem.getWidth())) {
								putIntoLuggage(y, x, insertingItem);
								done = true;
							}
						}
					}
				}
			}
			
			at--;
			printLuggage();
			System.out.print("\n");
		}
		
		
		/*
		int i = itemCount-1;
		
		boolean zeroSet = false;
		boolean checked = false;
		boolean needRotate = false;
		
		while(i >= 0) {
			int yZero = 0;
			int xZero = 0;
			for (int p = 0; p < lWidth; p++) {
				for (int q = 0; q < lHeight; q++) {
					if(!zeroSet && luggage[q][p] == 0) {
						int sum = 0;
						if(items.get(i).getHeight() <= lHeight-(q) && items.get(i).getWidth() <= lWidth-(p)) {
							for(int r = p; r < items.get(i).getHeight()-1; r++) {
								for(int s = q; s < items.get(i).getWidth()-1; s++) {
									if(luggage[r][s] != 0) {
										sum += luggage[r][s];
									}
								}
							}
						}
						else { sum = 1; needRotate = true;}
						
						if(sum == 0) {
							needRotate = false;
							yZero = q;
							xZero = p;
							zeroSet = true;
							checked = true;
						}
					}
				}
			}
			zeroSet = false;
			
			if(items.get(i).getHeight() <= lHeight-(yZero) && items.get(i).getWidth() <= lWidth-(xZero) && checked && !needRotate) {
				System.out.println(items.get(i).getId());
				System.out.println("------------------------------------------------------------");
				putIntoLuggage(yZero, xZero, i);
				i--;
			}else{
				items.get(i).rotate();
			}
		}
		*/
	}
	
	public static void quickSort(int min, int max) {
		int i = min;
		int j = max;
		
		int pivot = items.get(min).getArea();
		
		while (i <= j) {
			while(items.get(i).getArea() < pivot) {
				i++;
			}
			while(items.get(j).getArea() > pivot) {
				j--;
			}
			
			if( i <= j) {
				Collections.swap(items, i, j);
				
				i++;
				j--;
			}
		}
		
		if(min < j) {
			quickSort(min, j);
		}
		if(i < max) {
			quickSort(i, max);
		}
	}
	
	public static void putIntoLuggage(int yZero, int xZero, Item item) {
		for(int y = yZero; y < (yZero + item.getHeight()); y++) {
			for(int x = xZero; x < (xZero + item.getWidth()); x++) {
				luggage[y][x] = item.getId();
			}
		}
	}
	
	public static boolean fitsIntoLuggage(int pozY, int pozX, int h, int w) {
		if(pozY + h < lHeight-1 && pozX + w < lWidth-1) {
			int sum = 0;
			
			for(int y = pozY; y < h; y++) {
				for(int x = pozX; x < w; x++) {
					if(luggage[x][y] != 0) {
						sum++;
					}
				}
			}
			
			System.out.println(sum);
			
			if(sum == 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	
	// Console stuff
	
	public static void readItems() {
		sc = new Scanner(System.in);
		
		lHeight = sc.nextInt();
		lWidth = sc.nextInt();
		
		luggage = new int[lHeight][lWidth];
		
		itemCount = sc.nextInt();
		items = new ArrayList<Item>();
		
		for(int i = 0; i < itemCount; i++) {
			int h = sc.nextInt();
			int w = sc.nextInt();
			
			Item temp = new Item(h, w, i+1);
			temp.calcArea();
			items.add(temp);
		}
	}
	
	public static void printLuggage() {
		
		for(int i = 0; i < lHeight; i++) {
			for(int j = 0; j < lWidth; j++) {
				System.out.print(luggage[i][j]);
				if (j < lWidth-1) {
					System.out.print("\t");
				}
			}
			System.out.print("\n");
		}
	}
	
	public static void printItems() {
		System.out.println("id\t-->\tterulet");
		for (int i = 0; i < itemCount; i++) {
			System.out.println(items.get(i).getId() + "\t-->\t" +items.get(i).getArea() + "\tmeret: (" + items.get(i).getHeight() + ":" + items.get(i).getWidth() + ")");
		}
		System.out.print("\n");
	}
}
