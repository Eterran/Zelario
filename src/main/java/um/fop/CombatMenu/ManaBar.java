package CombatMenu;
public class ManaBar {
    public void HeroManaBar() 
    {
    	
    	int playerMana=getMP();
        int playerMaxMana=getMaxMP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) playerMana / playerMaxMana));

        System.out.print(" \033[0;36m Mana  : " +playerMana +"/"+playerMaxMana+" [");
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                System.out.print("|");
            } else {
                System.out.print(".");
            }
        }
        System.out.print("]\033[0m");
    }
    
    public void MonsterManaBar() {
    	
    	int monsterMana=getMP();
    		
        int monsterMaxMana=getMaxMP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) monsterMana / monsterMaxMana));

        System.out.print(" \033[0;36m Mana  : " +monsterMana +"/"+monsterMaxMana+" [");
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

    