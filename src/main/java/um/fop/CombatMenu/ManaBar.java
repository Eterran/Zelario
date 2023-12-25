package CombatMenu;

import Entitypack.Entity;

public class ManaBar {
    public void HeroManaBar(Entity player) 
    {
    	
    	int playerMana=player.getMP();
        int playerMaxMana=player.getMaxMP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) playerMana / playerMaxMana));

        System.out.printf(" \033[0;36m Mana  : %3s/%3s" ,playerMana, playerMaxMana+" [");
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                System.out.print("|");
            } else {
                System.out.print(".");
            }
        }
        System.out.print("]\033[0m");
    }
    
    public void MonsterManaBar(Entity monster) {
    	
    	int monsterMana=monster.getMP();
    		
        int monsterMaxMana=monster.getMaxMP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) monsterMana / monsterMaxMana));

        System.out.printf(" \033[0;36m Mana  : %3s/%3s" ,monsterMana ,monsterMaxMana+" [");
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

    