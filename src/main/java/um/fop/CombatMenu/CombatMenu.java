package CombatMenu;

import java.util.Scanner;
import java.io.IOException;

public class CombatMenu {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ASCII a=new ASCII();
		a.YouEnemy();
		
		System.out.print("+");
		
		int width = 123;
	    for (int i=0; i<width;i++) 
	    	System.out.print("-");
	    System.out.println("+");	    
	    System.out.println();

	    System.out.print(" \033[0;33m Level : 30");	//Example Level for hero
	    //System.out.print(" \033[0;33m Level : "+getLevel());
	    
	    System.out.println();
	    
	    HealthBar2 b=new HealthBar2();	//HPbar for hero
	    b.HeroHealthBar();
	    
	    for(int i=0;i<11;i++)
	    	System.out.print(" ");
	    
	    System.out.print("|");
	    for(int i=0;i<11;i++)
	    	System.out.print(" ");
	    
	    
	    b.MonsterHealthBar();
	    
	    System.out.println();//HPbar for monster
	    
	    ManaBar c=new ManaBar();
	    c.HeroManaBar();
	    for(int i=0;i<11;i++)
	    	System.out.print(" ");
	    
	    System.out.print("|");
	    for(int i=0;i<11;i++)
	    	System.out.print(" ");
	    
	    c.MonsterManaBar();
	    System.out.println();
	    System.out.println();
	    System.out.print("+ ");
	    for (int i=0; i<width/2;i++) 
	    	System.out.print("- ");
	    System.out.println("+");
	    
	    System.out.println();
	    System.out.println(" 1. Normal Attack");
	    
	  
	    if(this.isSkill1Unlocked==true)	
	    {
	    	System.out.println("\033[0m \033[0;32m2. "+getSkillOneName() +" \u001B[3m\t"+getSkill1Description());  
	    	System.out.println("Requires "+getSkill1Mp()+" mana");
	    }
	    
	    else
	    {
	    	System.out.println("\033[0m \033[0;90m2. "+getSkillOneName() +" \u001B[3m\t*Locked*");  
	    }
	    
		if(this.isSkill2Unlocked==true)	
		{
			System.out.println("\033[0m \033[0;35m3. "+getSkillTwoName() +" \u001B[3m\t"+getSkill2Description());  	
	    	System.out.println("Requires "+getSkill2Mp()+" mana");
		}
		
		else
		{
	    	System.out.println("\033[0m \033[0;90m3. "+getSkillTwoName() +" \u001B[3m\t*Locked*");  	
		}
		
	    if(this.isSkill2Unlocked==true)	
	    {
	    	System.out.println("\033[0m \033[0;34m4. "+getSkillThreeName()+" \u001B[3m\t"+getSkill3Description());  	
	    	System.out.print("Requires "+getSkill3Mp()+" mana");
	    	System.out.print("Cooldown");

	    }
	   	else
	   	{
	    	System.out.println("\033[0m \033[0;90m4. "+getSkillThreeName() +" \u001B[3m\t*Locked* \033[0m");  	
	   	}
	    
	    System.out.println();
	    System.out.print("+");
		width = 123;
	    for (int i=0; i<width;i++) 
	    	System.out.print("-");
	    System.out.println("+");	    
	    System.out.println();
	    System.out.print(">");

	    Scanner sc=new Scanner(System.in);
	    int input=sc.nextInt();
	    
	    
	    if(input==1){	
	    	
	    	System.out.println(getName()+" has dealt "+normalAttack+" damage!");
	    }
	    
	    else if(input==2) {
	    	
	    	System.out.println(getName()+" has dealt "+useSkill1+" damage!");

	    }
	    
	    else if(input==3) {
	    	System.out.println(getName()+" has dealt "+useSkill2+" damage!");

	    	
	    }
	    
	    else if(input==4) {
	    	
	    	System.out.println(getName()+" has dealt "+useSkill3+" damage!");
	    	
	    }
	    
	    else {
    		System.out.println("Invalid input. Please check again.");
	    }
	    

	}	

}
