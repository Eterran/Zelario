package Gamepack;
import java.util.Scanner;
import Entitypack.*;
import java.util.Random;
public class Game {
    public Game(){
        turn = true;
    }
    boolean turn = true;
    public void beginCombat(Entity player, Entity monster){
        //displayCombatMenu();
        System.out.println("You entered a combat with a " + monster.getName());
        Scanner s = new Scanner(System.in);
        if(player.getHP() <= 0) {
            s.close();
            loseCombat();
            return;
        }
        if(monster.getHP() <= 0) {
            s.close();
            winCombat(player, monster);
            return;
        }
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Escape");
        System.out.println("4. Heal" );
        if(player.isSkill1Unlocked)
            System.out.println("5. " + player.getSkillOneName());
        if(player.isSkill2Unlocked)
            System.out.println("6. " + player.getSkillTwoName());
        if(player.isSkill3Unlocked)
            System.out.println("7. " + player.getSkillThreeName());

        if(!player.isFrozen){
            while(turn){
                int choice;
                while(true){
                    try {
                        choice = Integer.parseInt(s.nextLine());
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                }
                int monsterPreviousHP = monster.getHP();
                switch(choice){
                    case 1:
                        player.normalAttack(monster);
                        endTurn();
                        break;
                    case 2:
                        //player.defend(monster);
                        endTurn();
                        break;
                    case 3:
                        //Escape();
                        endTurn();
                        return;
                    case 4:
                        player.healing();
                        endTurn();
                        break;
                    case 5:
                        if(!player.isSkill1Unlocked) {
                            System.out.println("Invalid input");
                            break;
                        }
                        if(player.getCDSkill1() == 0){
                            if(!player.isSilenced){
                                player.useSkill1(monster);
                                if(monster.checkMonsterHPChange(monsterPreviousHP)) 
                                    System.out.println("You used " + player.getSkillOneName() 
                                    + " and hit " + monster.getName() + " for " + (monsterPreviousHP-monster.getHP()) + "HP!");
                                else 
                                    System.out.println("You used " + player.getSkillOneName() 
                                    + " and buff youself for " + player.getSkill1() + "ATK!");
                                endTurn();
                            } else {
                                System.out.println("You are silenced!");
                            }
                        } else {
                            System.out.println("Your skill is not ready yet!");
                        }
                        break;
                    case 6:
                        if(!player.isSkill2Unlocked) {
                            System.out.println("Invalid input");
                            break;
                        }
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
                        if(!player.isSkill3Unlocked) {
                            System.out.println("Invalid input");
                            break;
                        }
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
                System.out.println(monster.getName() + " hits you for " + monster.normalAttack(player));
                endTurn();
            }else if(monster.getSkill2() != -1){
                if(enemyChoice < 40){
                    System.out.println(monster.getName() + " hits you for " + monster.normalAttack(player));
                    endTurn();
                } else if(enemyChoice < 70) {
                    if(monster.getCDSkill1() == 0)
                        monster.useSkill1(player);
                    else 
                        System.out.println(monster.getName() + " hits you for " + monster.normalAttack(player));
                    endTurn();
                } else if(enemyChoice < 100) {
                    if(monster.getCDSkill2() == 0)
                        monster.useSkill2(player);
                    else 
                        System.out.println(monster.getName() + " hits you for " + monster.normalAttack(player));
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
        //displayLoseCombat();                                                                 // how to handle losing?
        System.out.println("You lost!");
    }
}
