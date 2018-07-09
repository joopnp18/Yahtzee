package nextProgramLessenJoop;

import java.util.Random;
import java.util.Scanner;
//OKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAYOKAY
class Mastermind
{
	static String[] code = new String[4];					// de te raden code
	static String[] poging = new String[4];					// poging van speler
	static boolean[] positieCodeOkay = new boolean[4];		// houdt ontdekte posities in code bij
	static boolean[] positiePogingOkay = new boolean[4];	// houdt ontdekte posities in code bij
	static int zwart = 0, wit = 0;							// feedback pins
	static Scanner invoer = new Scanner(System.in);	
	
	static void maakCode()								// genereert 4-letter code
	{
		Random toeval = new Random();
		for (int index = 0; index < 4; index++)
		{
			int getal = toeval.nextInt(6) + 10;			// waarden van 10 - 15, ...
			String hex = Integer.toHexString(getal);	// converteren naar hex, ...
			code[index] = hex;							// worden opgeslagen in array
		}		
	}
	
	static void printArray(String[] reeks)		// print hele array, ...
	{
		for (String valPos : reeks)
		{
			System.out.print(valPos +  " ");	// op één regel
		}
		System.out.println();
	}
	
	static void vraagPoging()	// vraag speler om een poging
	{
		System.out.println("Geef een code van vier letters, gescheiden door spaties:");
		
		for (int index = 0; index < 4; index++)
		{
			poging[index] = invoer.next();
		}
	}
	
	static void telZwart()		// bepaal het aantal letters uit de poging op de juiste plaats
	{
		for (int indexPoging = 0; indexPoging < 4; indexPoging++)
		{
			if(poging[indexPoging].equals(code[indexPoging]))
			{
				zwart++;
				positieCodeOkay[indexPoging] = true;
				positiePogingOkay[indexPoging] = true;
			}
		}		
	}
	
	static void telWit()		// bepaal het aantal letters dat nu nog in de code voorkomt
	{
		for (int indexPoging = 0; indexPoging < 4; indexPoging++)	// loopt over de poging
		{
			for (int indexCode = 0; indexCode < 4; indexCode++)		// loopt over de code
			{
				if (poging[indexPoging].equals(code[indexCode]) && !positiePogingOkay[indexPoging] && !positieCodeOkay[indexCode])
				{
					wit++;
				}
			}			
		}		
	}
	
	static void feedbackNaarSpeler()
	{
		if (zwart != 4)
		{
			System.out.println("\tJe hebt er " + zwart + " op de juiste plaats.");
			System.out.println("\tJe hebt er " + wit + " op de verkeerde plaats.");			
		}
		else
		{
			System.out.println("Je hebt de code ontdekt.");
		}
	}
	
	public static void main(String[] args)
	{
		maakCode();
		printArray(code);						// check; deze regel verwijderen bij oplevering

		while (zwart < 4)
		{
			vraagPoging();
			wit = zwart = 0;
			telZwart();
			telWit();
			feedbackNaarSpeler();
		}

		invoer.close();
		
		System.out.println("\n=================");
		System.out.println("Mastermind klaar.");
	}
}