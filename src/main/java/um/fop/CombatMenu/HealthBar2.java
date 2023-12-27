package CombatMenu;
import Entitypack.Entity;
public class HealthBar2 {
    public void HeroHealthBar(Entity player) 
    {
    	
    	int playerHealth= player.getHP();
        int playerMaxHealth= player.getMaxHP(); 
        System.out.println("Player max health: " + playerMaxHealth);
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) playerHealth / playerMaxHealth));

        System.out.printf(" \033[0;31m Health: %3s/%3s" ,playerHealth,playerMaxHealth+" [");
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                System.out.print("|");
            } else {
                System.out.print(".");
            }
        }
        System.out.print("]\033[0m");
    }
    
    public void MonsterHealthBar(Entity monster) {
    	
    	int monsterHealth=monster.getHP();
        int monsterMaxHealth=monster.getMaxHP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) monsterHealth / monsterMaxHealth));

        System.out.printf(" \033[0;31m Health: %3s/%3s" ,monsterHealth, monsterMaxHealth+" [");
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                System.out.print("|");
            } else {
                System.out.print(".");
            }
        }
        System.out.print("]\033[0m");
    }
}

    