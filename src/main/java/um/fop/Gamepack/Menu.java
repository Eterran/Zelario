package Gamepack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Menu {
    public static void main(String[] args) throws FileNotFoundException {

		File file=new File("ASCII_Menu_Logo.txt");
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

	    //promt user to enter 1(New Game) or 2(Load Game) or 3(Quit) or
	    Scanner read=new Scanner(System.in);
	    int input=read.nextInt();
	    //note to Austin: I guess you will have to link this section(input==1 & input==2) to the database
	    if(input==1) {

	    }

	    else if(input==2) {

	    }

	    else if(input==3){
	    	System.exit(0);
	    }

	    else {
	    	while(input!=1&&input!=2&&input!=3)
	    	{
	    		System.out.println("Invalid input. Please check again.");
	            System.out.print(">");

	            input=read.nextInt();
	            if(input==1) {
	            }

	            else if(input==2) {

	            }

	            else if(input==3){
	            	System.exit(0);
	            }
	    	}
	    }

	    sc.close();

	}
}
