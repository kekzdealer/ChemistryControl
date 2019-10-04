package logic;

public class Item implements Comparable<Item> {
	
	public String name;
	public int amount;
	
	public Item(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public Item() {
		
	}
	
	@Override
	public int compareTo(Item o) {
		
		final int diff = amount - o.amount;
		
		if(diff == 0) {
			return 0;
		} else if (diff > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
}
