package zeeslag;

public class Ship {
	private String name;
	private int size;
	private int hits;
	
	public Ship(String name, int size) {
		this.name = name;
		this.size = size;
		hits = 0;
	}

	public char getChar() {
		 char result = '?';
		 if (name.length() > 0) {
			 result = name.charAt(0);
		 }
		 return result;
	}
	
	public int getSize() {
		return size;
	}

	public void hit() {
		hits++;
	}
	
	public boolean isSunk() {
		return (hits == size); 
	}
	
}
