package Gamepack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Menu {
    public static void startingScreen() throws FileNotFoundException {
		File file=new File("src\\main\\java\\um\\fop\\ASCII\\Menu Logo ASCII.txt");
		Scanner sc=new Scanner(file);
		while(sc.hasNextLine())
		{
			String data=sc.nextLine();
			System.out.println(data);
		}

		System.out.print("+");

		int width = 86;
	    for (int a=0; a<width;a++)
	    	System.out.print("-");
	    System.out.println("+");

	    for (int a=0; a<30;a++)
	    	System.out.print(" ");
	    System.out.println("-- Text-Based RPG -- ");


	    System.out.print("+");
	    for (int a=0; a<width/2;a++)
	    	System.out.print(" -");
	    System.out.println("+");

	    System.out.println("1. New Game");
	    System.out.println("2. Load Game");
	    System.out.println("3. Quit");

	    System.out.print("+");
	    for (int a=0; a<width/2;a++)
	    	System.out.print(" -");
	    System.out.println("+");

	    System.out.println("");
	    System.out.print(">");
	    sc.close();
	}
}
