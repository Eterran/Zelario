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

        if(!player.isFrozen){
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
                    case 4:
                        player.healing();
                        endTurn();
                        break;
                    case 5:
                        if(player.getCDSkill1() == 0){
                            if(!player.isSilenced){
                                player.useSkill1(monster);
                                endTurn();
                            } else {
                                System.out.println("You are silenced!");
                            }
                        } else {
                            System.out.println("Your skill is not ready yet!");
                        }
                        break;
                    case 6:
                        if(player.getCDSkill2() == 0){
                            if(!player.isSilenced){
                                player.useSkill2(monster);
                                endTurn();
                            } else {
                                System.out.println("You are silenced!");
                            }
                        } else {
                            System.out.println("Your skill is not ready yet!");
                        }
                        break;
                    case 7:
                        if(player.getCDSkill3() == 0){
                            if(!player.isSilenced){
                                player.useSkill3(monster);
                                endTurn();
                            } else {
                                System.out.println("You are silenced!");
                            }
                        } else {
                            System.out.println("Your skill is not ready yet!");
                        }
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }
        } else {
            System.out.println("You are frozen!");
            endTurn();
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
                } else if(enemyChoice < 70) {
                    monster.useSkill1(player);
                    endTurn();
                } else if(enemyChoice < 100) {
                    monster.useSkill2(player);
                    endTurn();
                }
            }
        }
        CDandStatus(player, monster);
    }
    public void CDandStatus(Entity player, Entity monster){
        player.CDDecrement();
        monster.CDDecrement();
        player.applyEffects();
        monster.applyEffects();
        player.tickStatus();
        monster.tickStatus();
    }
    public void endTurn(){
        turn = !turn;
    }
    public void winCombat(Entity player, Entity monster){ 
        //displayWinCombat(player.gainEXP(monster));                                           // win screen?
        System.out.println("You have defeated " + monster.getName());
        System.out.println("You receive " + player.gainEXP(monster) + "EXP!");
        player.clearAllStatus();
        
    }
    public void loseCombat(){
        displayLoseCombat();                                                                 // how to handle losing?
    }
}
