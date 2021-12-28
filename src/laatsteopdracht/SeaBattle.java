package laatsteopdracht;

import java.util.Scanner;

public class SeaBattle {
	
	public boolean CHEAT = false;
	private Field[] fields;
	private Player[] players;
	private int numberOfPlayers;
	private Scanner scanner;
	private int turn; // wie is aan de beurt

	public SeaBattle() {
		scanner = new Scanner(System.in);
		turn = 0;
	}
	
	private void changeTurn() {
		turn = (turn + 1) % numberOfPlayers; // 0 en 1 
	}

	public void play() {
		System.out.println("Welkom bij het spelletje Zeeslag!");
		
		inputCheatMode();
		
		if(players == null) {
			createPlayers();
		}
		createFields();


		
		boolean run = true;
		String position = "?";
		turn = 0;
		
		while (run) {
			// speel
			if (numberOfPlayers >= 2) {
				System.out.println("\n*** Aan de beurt : " + players[turn].getName() + " ***\n");
			}
			
			fields[turn].print(false);
			if (CHEAT) {
				System.out.println("");
				fields[turn].print(true);
			}
			
			position = players[turn].getInput();
			if (position.equals("quit")) {
				run = false;
				break;
			}
			if (fields[turn].positionWasShot(position)) {
				System.out.println("Deze coordinaat was al beschoten, kies een andere!");
			} else {
				fields[turn].fire(position);
				
				if (fields[turn].areAllShipsSunk()) {
					// afgelopen, winnaar is bekend
					fields[turn].print(false);
					System.out.println("Alle schepen zijn gezonken!");
					run = false;
				}
				if (CHEAT) {
					if (fields[turn].isAtLeastOneShipSunk()) {
						// we stoppen na 1 ship sunk in cheat mode
						System.out.println("RAAK!!!!!!!!!");
						System.out.println("Er is een ship gezonken, we stoppen");
						fields[turn].print(false); // laatse keer afdrukken
						run = false;
					}
				}
			}
			
			if (numberOfPlayers >= 2) {
				// met 2 spelers
				if (fields[turn].lastShotWasHit(position)) {
					// raak, niet wisselen van beurt
					
					if (run == false) {
						// het spel is afgelopen
						System.out.println(players[turn].getName() + ", heeft gewonnen!");
						break;
					} else {
						System.out.println("RAAK, schiet nog een keer!");
					}
					fields[turn].isOneShipSunk();
					
				} else {
					System.out.println("MIS, de beurt wisselt!");
					System.out.println("*Dit is nu het speelveld van " + players[turn].getName() + "*");
					fields[turn].print(false);
					changeTurn();
				}
			} else {
				// met 1 speler
				if (fields[turn].lastShotWasHit(position)) {
					fields[turn].isOneShipSunk();
					if (run == false) {
						System.out.println(players[turn].getName() + ", je hebt gewonnen!");
					} else {
						System.out.println("RAAK! Ga zo door!");
					}
				} else {
					System.out.println("MIS! Schiet nog een keer");
					System.out.println("*Dit is nu het speelveld van " + players[turn].getName() + "*");
					fields[turn].print(false);
				}
				
			}
//			fields[turn].print(false);
			
			
		} // end while
		
		if (inputPlayAgain()) {
			//herhalen
			play();
			
		} else {
			System.out.println("Bedankt voor het spelen van het spelletje Zeeslag.");
		}
		
		
	} // end play method
	
	private boolean inputPlayAgain() {
		boolean again = false;
		boolean repeat = true;
		String answer;
		while (repeat) {
			System.out.print("Wil je nog een keer spelen? (J/N): ");
			answer = scanner.nextLine().toLowerCase();
			
			if (answer.equals("j")) {
				repeat = false;
				again = true;
				break;
			} else if (answer.equals("n")) {
				repeat = false;
				again = false;
				break;
			} else {
				System.out.println("Je heb niet de juiste input gegeven");
			}
		}
		return again;
	}

	private void inputCheatMode() {
		boolean repeat = true;
		String answer;
		while (repeat) {
			System.out.print("Wil je CHEAT mode aan? (j/n): ");
			answer = scanner.nextLine().toLowerCase();
			
			if (answer.equals("j")) {
				CHEAT = true;
				repeat = false;
				break;
			} else if (answer.equals("n")) {
				repeat = false;
				CHEAT = false;
				break;
			} else {
				System.out.println("Jouw input was niet goed. Kies opnieuw");
			}
		}
		
	}

	private void createPlayers() {
		numberOfPlayers = inputNumberPlayers();
		players = new Player[numberOfPlayers];
		
		for (int i = 0; i < numberOfPlayers; i++) {
			players[i] = new Player();
			players[i].inputName(i+1);
		}
		
	}
	
	private void createFields() {
		fields = new Field[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			fields[i] = new Field();	
		}
	}

	private int inputNumberPlayers() {
		int result = 0;
		boolean repeat = true;
		String answer;
		
		while (repeat) {
			System.out.print("Met hoeveel spelers wil je spelen (1 of 2): ");
			answer = scanner.nextLine().toLowerCase();
			
			if (answer.length() != 1 || (!answer.equals("1") && !answer.equals("2"))) {
				// fout fout fout
				System.out.println("Je hebt niet de juiste keuze gemaakt.");
			} else {
				// goed :)
				repeat = false;
				result = Integer.parseInt(answer);
				break;
			}
		
		}
		
		return result;
	}
}
















