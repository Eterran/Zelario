package Gamepack;
import java.util.Scanner;
import Entitypack.Entity;
import java.util.Random;
public class Game {
    Game(){
        turn = true;
    }
    boolean turn = true;
    public void beginCombat(Entity player, Entity monster){
        //displayCombatMenu();
        Scanner s = new Scanner(System.in);
        if(player.getHP() <= 0) {
            loseCombat();
        }
        if(monster.getHP() <= 0) {
            winCombat(player, monster);
        }
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Escape");
        System.out.println("4. Heal" );
        System.out.println("5. " + player.getSkill1Name());
        System.out.println("6. " + player.getSkill2Name());
        System.out.println("7. " + player.getSkill3Name());
        while(turn){
            int choice = s.nextInt();                                      //Abnormal input handling required
            switch(choice){
                case 1:
                    player.defaultAttack(monster);
                    endTurn();
                    break;
                case 2:
                    player.defend(monster);
                    endTurn();
                    break;
                case 3:
                    Escape();
                    endTurn();
                    return;
                    break;
                case 4:
                    player.healing();
                    endTurn();
                    break;
                case 5:
                    if(player.getCDSkillOne() == 0){
                        player.useSkillOne(monster);
                        endTurn();
                    }
                    break;
                case 6:
                    if(player.getCDSkillTwo() == 0){
                        player.useSkillTwo(monster);
                        endTurn();
                    }
                    break;
                case 7:
                    if(player.getCDSkillThree() == 0){
                        player.useSkillThree(monster);
                        endTurn();
                    }
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        if(!turn){
            Random r = new Random();
            int enemyChoice = r.nextInt(99);
            if(monster.getSkill1() == -1){
                monster.defaultAttack(player);
                endTurn();
            }else if(monster.getSkill2() != -1){
                if(enemyChoice < 40){
                    monster.defaultAttack(player);
                    endTurn();
                }
            }
        }
    }
    public void endTurn(){
        turn = !turn;
    }
    public void winCombat(Entity player, Entity monster){ 
        //displayWinCombat(player.gainEXP(monster));                                           // win screen?
        System.out.println("You have defeated " + monster.getName());
        System.out.println("You receive " + player.gainEXP(monster) + "EXP!");
    }
    public void loseCombat(){
        displayLoseCombat();                                                                 // how to handle losing?
    }
}
