package nextProgramLessenJoop;

import java.util.Random;
import java.util.Scanner;

class Yahtzee
{
	public static void main(String[] args)
	{
		Dobbelsteen[] beker = new Dobbelsteen[5];			// beker voor 5 dobbelstenen maken
		bekerVullen(beker);									// en vullen
		
		spelen(beker);										// ... en spelen maar

		System.out.println(" <<< Uw eindresultaat.");		// afsluiten
	
		System.out.println("==============");
		System.out.println("Yahtzee Ready.");
	}
	
	static Dobbelsteen[] bekerVullen(Dobbelsteen[] b)		// vult de beker met 5 dobbelstenen
	{
		for (int index = 0; index < 5; index++)
		{
			b[index] = new Dobbelsteen();
		}
		return b;
	}

	static Dobbelsteen[] bekerGooien(Dobbelsteen[] b)		// gooit de beker, waarbij de vastgezette dobbelstenen NIET veranderen
	{
		for (int index = 0; index < 5; index++)
		{
			if (!b[index].vastzetten)
			{	
				b[index].waarde = b[index].gooien();
			}
		}
		return b;
	}
	
	static void stenenLatenZien(Dobbelsteen[] b)			// laat de worp zien
	{
		for (int index = 0; index < 5; index++)
		{
			System.out.print(b[index].waarde + "  ");
		}
	}
	
	static void spelen(Dobbelsteen[] b)
	{
		Scanner invoer = new Scanner(System.in);			// user invoer mogelijk maken
		String antwoord;									// en opvangen

		bekerGooien(b);										// beker gooien
		stenenLatenZien(b);									// worp laten zien
		
		for (int worp = 1; worp < 3; worp++)
		{
			System.out.println();
			for (int index = 0; index < 5; index++)			// vragen welke dobbelstenen vastgezet moeten worden
			{
				System.out.print("\t\tDe " + (index+1) + "e is een "+ b[index].waarde + ". Vastzetten? <j/n>");
				antwoord = invoer.next();
				if (antwoord.equals("j") | antwoord.equals("n"))
				{
					if (antwoord.equals("j"))
					{
						b[index].vastzetten = true;
					}
					else
					{
						b[index].vastzetten = false;
					}	
				}
				else
				{
					index--;
					System.out.println("Uw keuze wordt niet herkend; waarschijnlijk heeft u iets anders gekozen dan <j> of <n>");
				}

			}
			bekerGooien(b);
			stenenLatenZien(b);			
		}
		invoer.close();
	}
}

class Dobbelsteen
{
	int waarde;
	boolean vastzetten;
	
	int gooien()
	{
		Random r = new Random();
		waarde = (r.nextInt(6) + 1);
		return waarde;
	}
}
