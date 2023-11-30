package Gamepack;
import java.util.Scanner;
import Entitypack.Entity;
public class Game {
    boolean turn = true;
    public void beginCombat(Entity player, Entity monster){
        displayCombatMenu();
        Scanner s = new Scanner(System.in);
        if(player.getHP() <= 0) {
            loseCombat();
        }
        if(monster.getHP() <= 0) {
            winCombat();
        }
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Escape");
        System.out.println("4. " + );
        System.out.println("5. ");
        System.out.println("6. ");
        while(turn){
            int choice = s.nextInt();                                      //Abnormal input handling required
            switch(choice){
                case 1:
                    player.Attack();
                    endTurn();
                    break;
                case 2:
                    player.Defend();
                    endTurn();
                    break;
                case 3:
                    Escape();
                    endTurn();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
    public void endTurn(){
        turn = false;

    }
    public void winCombat(){ 
        displayWinCombat();                                             // win screen?

    }
    public void loseCombat(){
        displayLoseCombat();                                            // how to handle losing?
    }
}
