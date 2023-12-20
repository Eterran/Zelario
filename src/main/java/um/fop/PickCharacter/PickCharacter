package PickCharacter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import PickCharacter.Rogue;

public class PickCharacter {

	public static void main(String[] args)  throws FileNotFoundException{
		// TODO Auto-generated method stub
		File file=new File("Menu Logo ASCII.txt");
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
	  
	    
	    if(input==1)
	    {
	    	Rogue.PickRogue();
	    }
	    
	    else if(input==2){
	    	Warrior.PickWarrior();
	    }
	    
	    else if(input==3) {
	    	Archer.PickArcher();
	    	
	    }
	    
	    else if(input==4)
	    {
	    	Mage.PickMage();
	    }
	    
	    else if(input==5) {
	    	Paladin.PickPaladin();
	    }
	    
	    else
	    {
	    	while(input!=1&&input!=2&&input!=3&&input!=4&&input!=5)
	    	{
	    		System.out.println("Invalid input. Please check again.");
	            System.out.print(">");

	            input=read.nextInt();
	            if(input==1) 
	            {
	            	Rogue.PickRogue();
	            }
	            
	            else if(input==2) 
	            {
	            	Warrior.PickWarrior();
	            }
	            
	            else if(input==3){
	            	Archer.PickArcher();
	            }
	            
	            
	    	    else if(input==4)
	    	    {
	    	    	Mage.PickMage();
	    	    }
	    	    
	    	    else if(input==5) {
	    	    	Paladin.PickPaladin();
	    	    }
	    	}
	    
	    }
	    sc.close();

	}

}
