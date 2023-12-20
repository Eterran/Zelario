package PickCharacter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Mage {

	public static void PickMage() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file=new File("Mage ASCII.txt");
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
	    
	    for (int a=0; a<38;a++) 
	    	System.out.print(" ");
	    System.out.println("-- Mage -- ");
	    
	    System.out.print("+");
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    System.out.println(" Skills:");
	    System.out.println(" ------");
	    System.out.println();
	    
	    System.out.println(" Poison: ");
	    System.out.println(" Fireball:");
	    System.out.println(" Frost:");
	    
	    System.out.print("+");
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    System.out.println(" Do you want to pick this character?");
	    System.out.println(" 1. Yes");
	    System.out.println(" 2. No");
	    System.out.println("");
	    System.out.print(">");
	    
	    Scanner input=new Scanner(System.in);
	    int choice=input.nextInt();
	    
	    if(choice==1)
	    {
	    	//go to map
	    }
	    
	    else if(choice==2)
	    {
	    	PickCharacter.main(null);
	    }

	    
	}
}