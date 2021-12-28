package zeeslag;

import java.util.HashMap;
import java.util.Random;

public class Field {

	// constants
	final private int fieldSize = 10;

	// instance variables
//	private Square[][] cel;
	private HashMap<String, Square> map;
	private Ship[] ships;

	public Field() {
		map = new HashMap<String, Square>();
//		cel = new Square[fieldSize][fieldSize];
		for (int r = 0; r < fieldSize; r++) {
			for (int c = 0; c < fieldSize; c++) {
//				cel[r][c] = new Square();
				String key = createKey(r, c);
				map.put(key, new Square());
			}
		}

		ships = new Ship[5];
		ships[0] = new Ship("Aircraft Carrier", 5);
		ships[1] = new Ship("Battleship", 4);
		ships[2] = new Ship("Submarine", 3);
		ships[3] = new Ship("Destroyer", 3);
		ships[4] = new Ship("Patrol Boat", 2);

		hideShips();

	}

	public void print(boolean isCheat) {
		
		for (int i = fieldSize; i > 0; i--) {
			if (i < fieldSize) {
				System.out.print(" ");
			}
			System.out.print(i);

			for (int c = 0; c < fieldSize; c++) {
				int r = i - 1;
				String key = createKey(r, c);
				System.out.print(" " + map.get(key).getContent(isCheat));

			}
			System.out.println("");

		}
		System.out.println("   a b c d e f g h i j");
	}

	private String createKey(int r, int c) {
		String key = (char) (c + 97) + String.valueOf(r + 1); // ASCI 97 = 'a'
		return key;
	}

	private void hideShips() {
		Random ran = new Random();
		boolean isHorz;
		int countAttempts = 0;

		for (int shipindx = 0; shipindx < ships.length; shipindx++) {
			if (ships[shipindx] == null) {
				continue; // naar volgende ship in de loop
			}
			int curSize = ships[shipindx].getSize();
			int r = 0;
			int c = 0;
			isHorz = ran.nextBoolean();
			boolean allCellsEmpty;
			boolean isPlaced;

			if (isHorz) {
				// horizontaal
				isPlaced = false;
				while (!isPlaced) {
					countAttempts++;
					c = ran.nextInt((fieldSize + 1) - curSize); // range 0 - sizeShip
					r = ran.nextInt(fieldSize); // range 0 - 9
					allCellsEmpty = true;
					// check of alle plekken vrij zijn
					for (int i = 0; i < curSize; i++) {
						String key = createKey(r, c + i);
						if (map.get(key).isEmpty() == false) {
							allCellsEmpty = false;
							break;
						}
//						if (cel[r][c+i].isEmpty() == false) {
//							allCellsEmpty = false;
//							break;
//						}
					}

					if (allCellsEmpty) {
						for (int i = 0; i < curSize; i++) {
							String key = createKey(r, c + i);
//							cel[r][c+i].setShip(ships[shipindx]);
							map.get(key).setShip(ships[shipindx]);
						}
						isPlaced = true;
					}
				}

			} else {
				// verticaal
				isPlaced = false;
				while (!isPlaced) {
					countAttempts++;
					r = ran.nextInt((fieldSize + 1) - curSize); // range 0 - sizeShip
					c = ran.nextInt(fieldSize); // range 0 - 9
					allCellsEmpty = true;
					// check of alle plekken vrij zijn
					for (int i = 0; i < curSize; i++) {
						String key = createKey(r + i, c);
						if (map.get(key).isEmpty() == false) {
							allCellsEmpty = false;
							break;
						}
					}

					if (allCellsEmpty) {
						for (int i = 0; i < curSize; i++) {
							String key = createKey(r + i, c);
							map.get(key).setShip(ships[shipindx]);
						}
						isPlaced = true;
					}
				}

			}
		}
		// System.out.println(countAttempts + " Attempst");
	}

	public void fire(String position) {
		int r = getRow(position);
		int c = getCol(position);
		String key = createKey(r, c);
		map.get(key).shoot();
//		cel[getRow(position)][getCol(position)].shoot();
	}

	public boolean positionWasShot(String position) {
		boolean result = false;
		int r = getRow(position);
		int c = getCol(position);
		String key = createKey(r, c);
		Square target = map.get(key);
		if (target.wasShot()) {
			// the square is al beschoten
			result = true;
		}
		return result;
	}

	private int getRow(String position) {
		int row = 0;

		if (position.length() == 2) {
			row = (int) position.charAt(1) - 49; // ASCII 49 = '1'
		} else {
			row = 9;
		}

		return row;
	}

	private int getCol(String position) {
		return (int) position.charAt(0) - 97; // ASCII 97 = 'a'
	}

	public boolean areAllShipsSunk() {
		boolean result = true;
		for (Ship s : ships) {
			if (s != null) {
				if (s.isSunk() == false) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	public boolean isAtLeastOneShipSunk() {
		boolean result = false;
		for (Ship s : ships) {
			if (s != null) {
				if (s.isSunk()) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

}
