package PickCharacter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import PickCharacter.Rogue;

public class Pick {

	int heroChoice=-1;
	public static void main(String[]args)  throws FileNotFoundException{
		// TODO Auto-generated method stub
		File file=new File("src\\main\\java\\um\\fop\\ASCII\\Menu Logo ASCII.txt");
		Scanner sc=new Scanner(file);
		while(sc.hasNextLine())
		{
			String data=sc.nextLine();
			System.out.println(data);
		}
		
		System.out.print("+");
		
		int width = 86;
	    for (int a=0; a<width/2;a++) 
	    	System.out.print("- ");
	    System.out.println("+");
	    
	    for (int a=0; a<30;a++) 
	    	System.out.print(" ");
	    System.out.println("-- Pick your class --");

		
	    System.out.print("+");
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    System.out.println(" 1. Rogue");
	    System.out.println(" 2. Warrior");
	    System.out.println(" 3. Archer");
	    System.out.println(" 4. Mage");
	    System.out.println(" 5. Paladin");


	    System.out.print("+");
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    System.out.println("");
	    System.out.print(">");
	    
	    Scanner read=new Scanner(System.in);
		int input = 0;
		while(true){
			try {
				input=read.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Invalid input.");
				read.nextLine();
			}
		}
		while(input<1 || input>5){
			System.out.println("Invalid input.");
			input=read.nextInt();
		}
		switch (input) {
			case 1:
				System.out.println("You have chosen Rogue");
				break;
			case 2:
				System.out.println("You have chosen Rogue");
				break;
			default:
			
				break;
		}
		
	    sc.close();

        

	}

	public  int getHeroChoice()
	{
		return heroChoice;
	}

	public  void setHeroChoice(int a)
	{
		heroChoice=a;
		return;
	}

}
