package PickCharacter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import PickCharacter.Rogue;

public class PickCharacter {

	int heroChoice=-1;
	public static void main(String[]args)  throws FileNotFoundException{
		// TODO Auto-generated method stub
		File file=new File("Zelario\\src\\main\\java\\um\\fop\\ASCII\\Menu Logo ASCII.txt");
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
	    int input=read.nextInt();
	  
	    
	    
		
	    sc.close();

	}
	public static void PickRogue() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file=new File("Zelario\\src\\main\\java\\um\\fop\\ASCII\\Rogue ASCII.txt");
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
	    System.out.println("-- Rogue -- ");
	    
	    System.out.print("+");
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    System.out.println(" Skills:");
	    System.out.println(" ------");
	    System.out.println();
	    
	    System.out.println(" Smite:");
	    System.out.println(" Backstab:");
	    System.out.println(" Shadowstep:");
	    
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

	public static void PickArcher() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file=new File("Zelario\\src\\main\\java\\um\\fop\\ASCII\\Archer ASCII.txt");
		Scanner sc=new Scanner(file);
		while(sc.hasNextLine())
		{
			String data=sc.nextLine();
			System.out.print("      ");

			System.out.println(data);
		}
		
		
		System.out.print("+");
		
		int width = 86;
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    for (int a=0; a<38;a++) 
	    	System.out.print(" ");
	    System.out.println("-- Archer -- ");
	    
	    System.out.print("+");
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    System.out.println(" Skills:");
	    System.out.println(" ------");
	    System.out.println();
	    
	    System.out.println(" Precise: ");
	    System.out.println(" Rapid Arrow:");
	    System.out.println(" Trick Shot:");
	    
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
	    	//setHeroChoice(1);
	    }
	    
	    else if(choice==2)
	    {
	    	PickCharacter.main(null);
	    }

	    
	}
	public static void PickMage() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file=new File("Zelario\\src\\main\\java\\um\\fop\\ASCII\\Mage ASCII.txt");
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
	    	
	    }
	    
	    else if(choice==2)
	    {
	    	PickCharacter.main(null);
	    }

	    
	}

	public static void PickWarrior() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file=new File("Zelario\\src\\main\\java\\um\\fop\\ASCII\\Warrior ASCII.txt");
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
	    System.out.println("-- Warrior -- ");
	    
	    System.out.print("+");
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    System.out.println(" Skills:");
	    System.out.println(" ------");
	    System.out.println();
	    
	    System.out.println(" Cha Cha Cha:");
	    System.out.println(" Furious Strike:");
	    System.out.println(" Shield Wall:");
	    
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
	public static void PickPaladin() throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		File file=new File("Zelario\\src\\main\\java\\um\\fop\\ASCII\\Paladin ASCII.txt");
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
	    System.out.println("-- Paladin -- ");
	    
	    System.out.print("+");
	    for (int a=0; a<width;a++) 
	    	System.out.print("-");
	    System.out.println("+");
	    
	    System.out.println(" Skills:");
	    System.out.println(" ------");
	    System.out.println();
	    
	    System.out.println(" Rage:");
	    System.out.println(" Holy Smite:");
	    System.out.println(" Divine Shield:");
	    
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
