package laatsteopdracht;

public class Square {
	
	//instance variables
	private Ship ship;
	private boolean m_wasShot;
	
	public Square() {
		m_wasShot = false;
		ship = null;
	}
	
	public char getContent(boolean isCheat) {
		char result = '?';
		
		if (isCheat) {
			// cheat mode
			if (ship != null) {
				result = ship.getChar();
			} else {
				result = '~';
			}
			
		}
		else
		{
			// normal mode
			if (m_wasShot) {
				if (ship != null) {
					result = '*';
				} else {
					result = '~';
				}
			} else {
				result = '.';
			}
		}
		return result;
	}
	
	public void setShip(Ship pship) {
		this.ship = pship;
	}

	public boolean isEmpty() {
		return (ship == null);
		// return true als er geen ship in zit
		// return false als er wel een ship in zit
	}

	public void shoot() {
		m_wasShot = true;
		if (ship == null) {
			return;
		} 
		ship.hit();
	}

	public boolean wasShot() {
		return m_wasShot;
	}

	public boolean wasHit() {
		if (ship != null && wasShot()) {
			// ship is geraakt
			return true;
		}
		return false;
	}
}
