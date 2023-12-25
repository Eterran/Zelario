package CombatMenu;
public class HealthBar2 {
    public void HeroHealthBar() 
    {
    	
    	int playerHealth=getHP();
        int playerMaxHealth=getMaxHP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) playerHealth / playerMaxHealth));

        System.out.print(" \033[0;31m Health: " +playerHealth +"/"+playerMaxHealth+" [");
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                System.out.print("|");
            } else {
                System.out.print(".");
            }
        }
        System.out.print("]\033[0m");
    }
    
    public void MonsterHealthBar() {
    	
    	int monsterHealth=getHP();
        int monsterMaxHealth=getMaxHP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) monsterHealth / monsterMaxHealth));

        System.out.print(" \033[0;31m Health: " +monsterHealth +"/"+monsterMaxHealth+" [");
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

    