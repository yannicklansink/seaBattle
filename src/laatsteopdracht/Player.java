package laatsteopdracht;

import java.util.Scanner;

public class Player {

	private Scanner scanner;
	private String name;
	
	public Player() {
		scanner = new Scanner(System.in);
		name = "unknown";
	}
	
	public String getInput() {
		String input = "?";
		boolean repeat = true;
		while(repeat) {
			System.out.print("Op welk veld wil je schieten " + getName() + "?: ");
			input = scanner.nextLine().toLowerCase();
			
			//coordianaat controle	
			if (input.equals("quit")) {
				break;
			}
			
			if (input.length() < 2 || input.length() > 3) {
				// te klein of te groot
				System.out.println("fout: te groot of the kleine input");
			} else if (input.charAt(0)<'a' || input.charAt(0)>'j') {
				System.out.println("fout: onbekende kolom");
			} else if (input.length()==2 &&(input.charAt(1)<'1' || input.charAt(1)>'9')) {
				// rij klopt niet
				System.out.println("fout: onbekende rij");
			} else if (input.length()==3 && (input.charAt(1)!='1' || input.charAt(2)!='0')) {
				// rij klopt niet
				System.out.println("fout: onbekende rij");
			} else {
				// voorwaarden kloppen
				repeat = false;
			}
		}
		
		return input; // a-j en 1-10
	}

	public String inputName(int nr) {
		String name = "?";
		
		boolean repeat = true;
		while (repeat) 
		{
			System.out.print("Speler " + nr + ", wat is je naam?:");
			name = scanner.nextLine();
			if (name.length()<=1) 
			{
				System.out.println("fout: de naam is te kort");
			} else if (containsOnlySpaces(name)) 
			{
				System.out.println("fout: de naam mag niet alleen uit spaties bestaan");
			}
			else 
			{
				repeat = false;
				this.name = name;
			}
		}
		
		return name;
		
	}

	private boolean containsOnlySpaces(String s) {
		boolean result = true;
		
		for(int i = 0; i <s.length(); i++) {
			if (s.charAt(i)!=' ') {
				result = false;
				break;
			}
		}
		
		return result;
	}

	public boolean isUnknown() {
		boolean known = false;
		if (this.name.equals("unknown"))
		{		
			known = true;
		}
		return known;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
}











