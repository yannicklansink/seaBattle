package zeeslag;

public class SeaBattle {
	
	public static final boolean CHEAT = true;

	private Field field;
	private Player player;

	public SeaBattle() {
		field = new Field();
		player = new Player();
	}

	public void play() {
		System.out.println("Welkom bij het spelletje Zeeslag!");

		if (player.isUnknown()) {
			// gebeurt als een speler het eerste spel start
			player.getName();
		}
		
		boolean run = true;
		String position = "?";
		while (run) {
			// speel
			field.print(false);
			if (SeaBattle.CHEAT) {
				System.out.println("");
				field.print(true);
			}
			
			position = player.getInput();
			if (position.equals("quit")) {
				run = false;
				break;
			}
			if (field.positionWasShot(position)) {
				System.out.println("Deze coordinaat was al beschoten, kies een andere!");
			} else {
				field.fire(position);
				if (field.areAllShipsSunk()) {
					// afgelopen, winnaar is bekend
					field.print(false);
					System.out.println("Alle schepen zijn gezonken!");
					run = false;
				}
				if (SeaBattle.CHEAT) {
					if (field.isAtLeastOneShipSunk()) {
						// we stoppen na 1 ship sunk in cheat mode
						System.out.println("Er is een ship gezonken, we stoppen");
						field.print(false); // laatse keer afdrukken
						run = false;
					}
				}
			}
		}

		if (player.getPlayAgain()) {
			//herhalen
			play();
			
		} else {
			System.out.println("Bedankt voor het spelen van het spelletje Zeeslag.");
		}
		
		
	}
}
