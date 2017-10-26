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
		
		sortItems();
		
		//printItems();
		
		putItemsIntoLuggage();
		
		printLuggage();
	}
	
	// method of algorithm
	
	public static void putItemsIntoLuggage() {
		int at = itemCount-1;
		boolean done = false;
		boolean fits = false;
		
		while(at >= 0) {
			Item insertingItem = items.get(at);
			done = false;
			
			for(int y = 0; y < lHeight; y++) {
				for(int x = 0; x < lWidth; x++) {
					if(luggage[y][x] == 0 && !done) {
						fits = fitsIntoLuggage(y, x, insertingItem.getHeight(), insertingItem.getWidth());
						if(fits) {
							putIntoLuggage(y, x, insertingItem);
							done = true;
						}
						else {
							insertingItem.rotate();
							fits = fitsIntoLuggage(y, x, insertingItem.getHeight(), insertingItem.getWidth());
							if(fits) {
								putIntoLuggage(y, x, insertingItem);
								done = true;
							}
						}
					}
				}
			}
			
			//System.out.println(insertingItem.getId());
			//printLuggage();
			
			at--;
		}
	}
	
	public static void getEmRight() {
		for(int i = 0; i < itemCount; i++) {
			if(items.get(i).getHeight() < items.get(i).getWidth()) {
				items.get(i).rotate();
			}
		}
	}
	
	public static void sortItems() {
		getEmRight();
		
		quickSortByLongSide(0, itemCount-1);
		
		//printItems();
		
		int remaining = itemCount;
		int lastPoz = 0;
		
		while(remaining > 0) {
			int cnt = 0;
			int startOfSection = lastPoz;
			int comp = items.get(lastPoz).getHeight();
			
			for(int i = 0; i < itemCount; i++) {
				if(items.get(i).getHeight() == comp) {
					cnt++;
				}
			}
			
			//System.out.println(lastPoz);
			
			lastPoz += cnt;
			
			//System.out.println(lastPoz + "\n");
			
			quickSortByShortSide(startOfSection, lastPoz-1);
			remaining -= cnt;
		}
		
		//printItems();
		
	}
	
	public static void quickSortByLongSide(int min, int max) {
		int i = min;
		int j = max;
		
		int pivot = items.get(min).getHeight();
		
		while (i <= j) {
			while(items.get(i).getHeight() < pivot) {
				i++;
			}
			while(items.get(j).getHeight() > pivot) {
				j--;
			}
			
			if( i <= j) {
				Collections.swap(items, i, j);
				
				i++;
				j--;
			}
		}
		
		if(min < j) {
			quickSortByLongSide(min, j);
		}
		if(i < max) {
			quickSortByLongSide(i, max);
		}
	}
		
	public static void quickSortByShortSide(int min, int max) {
		int i = min;
		int j = max;
		
		int pivot = items.get(min).getWidth();
		
		while (i <= j) {
			while(items.get(i).getWidth() < pivot) {
				i++;
			}
			while(items.get(j).getWidth() > pivot) {
				j--;
			}
			
			if( i <= j) {
				Collections.swap(items, i, j);
				
				i++;
				j--;
			}
		}
	
		if(min < j) {
			quickSortByShortSide(min, j);
		}
		if(i < max) {
			quickSortByShortSide(i, max);
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
		if(pozY + h - 1 < lHeight && pozX + w - 1 < lWidth) {
			int sum = 0;
			
			for(int y = pozY; y < pozY+h; y++) {
				for(int x = pozX; x < pozX+w; x++) {
					if(luggage[y][x] != 0) {
						sum+=luggage[y][x];
					}
				}
			}
			
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
