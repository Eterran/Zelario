package Gamepack;

import java.util.Scanner;
import Entitypack.*;
import Entitypack.Monsterpack.*;
import Mappack.RandomMonsterMap;
import CombatMenu.CombatMenu;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public class Game {
    public Game(Entity player) {
        turn = true;
        isMonsterAlive = true;
        initialLevel = player.getLevel();
    }
    int initialLevel;
    boolean isMonsterAlive = true;
    boolean turn = true;

    public void beginCombat(Entity player, Entity monster, JTextPane textPane, ConsoleToGUI consoleToGUI, JFrame frame) {
        // frame.setVisible(true);
        // RandomMonsterMap.getMapFrame().setVisible(false);
        System.out.println("You entered a combat with a " + monster.getName());
        for (int i = 0; i < 123; i++)
        System.out.print("-");
         System.out.println("+");
        System.out.println();
        
        turn = true;
        isMonsterAlive = true;
        initialLevel = player.getLevel();
        while (isMonsterAlive) {
            try{
                CombatMenu.displayCombatMenu(player, monster, textPane, frame);
                Thread.sleep(100);
            }catch(IOException | InterruptedException e){  
                e.printStackTrace();
            }
            int temp = checkWinLose(player, monster, consoleToGUI);
            if(temp == 1){
                frame.setVisible(true);
                RandomMonsterMap.getMapFrame().setVisible(false);
                return;
            } else if (temp == -1){
                return;
            }
            if (!player.isFrozen && !player.isStunned) {
                while (turn) {
                    String choice = "";
                    try {
                        do {
                            choice = consoleToGUI.getNextInput();
                            if (!choice.matches("^[1-7]$")) {
                                System.out.println("Invalid input.");
                            }
                        } while (!choice.matches("^[1-7]$"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int monsterPreviousHP = monster.getHP();
                    switch (choice) {
                        case "1":
                            System.out.println("You hit the " + monster.getName() + " for "
                                    + player.normalAttack(monster) + "HP!");
                            endTurn();
                            break;
                        case "2":
                            player.defend(monster);
                            endTurn();
                            break;
                        case "3":
                            // Escape();
                            endTurn();
                            return;
                        case "4":
                            player.healing();
                            endTurn();
                            break;
                        case "5":
                            if (!player.isSkill1Unlocked) {
                                System.out.println("You have not unlocked this skill yet!");
                                break;
                            }
                            
                            if (player.getCDSkill1() == 0 && player.checkMana(player.getSkill1Mp())) { //cd remain 4
                                if (!player.isSilenced) {
                                    player.useSkill1(monster);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP))
                                        System.out.println("You used " + player.getSkillOneName()
                                                + " and hit " + monster.getName() + " for "
                                                + (monsterPreviousHP - monster.getHP()) + "HP!");
                                    else
                                        System.out.println("You used " + player.getSkillOneName()
                                                + " and buff youself for " + player.getSkill1() + "ATK!");
                                    endTurn();
                                } else {
                                    System.out.println("You are silenced!");
                                }
                            } else {
                                //System.out.println(player.getMP());//chechking
                                //System.out.println(player.getSkill1Mp());//chechking
                                //System.out.println(player.getCDSkill1());//checking current cd
                                if(player.getMP() < player.getSkill1Mp()){
                                    System.out.println("You do not have enough mana!");
                                }
                                else if(player.getCDSkill1() != 0){
                                    System.out.println("Your skill is not ready yet!");
                                }
                            }
                            break;
                        case "6":
                            if (!player.isSkill2Unlocked) {
                                System.out.println("You have not unlocked this skill yet!");
                                break;
                            }
                            if (player.getCDSkill2() == 0 && player.checkMana(player.getSkill1Mp())) {
                                if (!player.isSilenced) {
                                    player.useSkill2(monster);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP))
                                        System.out.println("You used " + player.getSkillTwoName()
                                                + " and hit " + monster.getName() + " for "
                                                + (monsterPreviousHP - monster.getHP()) + "HP!");
                                    else
                                        System.out.println("You used " + player.getSkillTwoName()
                                                + " and buff youself for " + player.getSkill2() + "ATK!");
                                    endTurn();
                                } else {
                                    System.out.println("You are silenced!");
                                }
                            } else {
                                System.out.println("Your skill is not ready yet!");
                            }
                            break;
                        case "7":
                            if (!player.isSkill3Unlocked) {
                                System.out.println("You have not unlocked this skill yet!");
                                break;
                            }
                            if (player.getCDSkill3() == 0 && player.checkMana(player.getSkill1Mp())) {
                                if (!player.isSilenced) {
                                    player.useSkill3(monster);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP))
                                        System.out.println("You used " + player.getSkillThreeName()
                                                + " and hit " + monster.getName() + " for "
                                                + (monsterPreviousHP - monster.getHP()) + "HP!");
                                    else
                                        System.out.println("You used " + player.getSkillThreeName()
                                                + " and buff youself for " + player.getSkill1() + "ATK!");
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
            } else if(player.isFrozen) {
                System.out.println("You are frozen!");
                endTurn();
            } else if(player.isStunned) {
                System.out.println("You are stunned!");
                endTurn();
            }
            temp = checkWinLose(player, monster, consoleToGUI);
            if(temp == 1){
                frame.setVisible(true);
                RandomMonsterMap.getMapFrame().setVisible(false);
                return;
            } else if (temp == -1){
                return;
            }
            if (!monster.isFrozen && !monster.isStunned) {
                if (!turn && isMonsterAlive) {
                    Random r = new Random();
                    int enemyChoice = r.nextInt(99);
                    if (monster.getSkill1() == -1) {
                        System.out.println(monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                        endTurn();
                    } else if (monster.getSkill1() != -1) {
                        if (enemyChoice < 40) {
                            System.out.println(monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                            endTurn();
                        } else if (enemyChoice < 70) {
                            if (monster.getCDSkill1() == 0 && monster.checkMana(monster.getSkill1Mp()))
                                System.out.println(
                                        monster.getName() + " used " + monster.getSkillOneName() 
                                        + "hits you for " + monster.useSkill1(player) + "HP!");
                            else
                                System.out.println(
                                        monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                            endTurn();
                        } else if (enemyChoice < 100) {
                            if (monster.getCDSkill2() == 0)
                                System.out.println(
                                        monster.getName() + " used " + monster.getSkillTwoName() 
                                        + "hits you for " + monster.useSkill2(player) + "HP!");
                            else
                                System.out.println(
                                        monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                            endTurn();
                        }
                    }
                }
            } else if(monster.isFrozen){
                System.out.println(
                        monster.getName() + " is frozen and cannot move! ");
                endTurn();
            } else if(monster.isStunned){
                System.out.println(
                        monster.getName() + " is stunned and cannot move! ");
                endTurn();
            }
            CDandStatus(player, monster);
        }
    }
    public void CDandStatus(Entity player, Entity monster) {
        player.CDDecrement();
        monster.CDDecrement();
        player.applyEffects();
        monster.applyEffects();
        player.tickStatus();
        monster.tickStatus();
        player.recoverMana();
        monster.recoverMana();
    }
    public void endTurn() {
        turn = !turn;
    }
    public void winCombat(Entity player, Entity monster) {
        displayWinCombat();
        isMonsterAlive = false;
        System.out.println("+You have defeated " + monster.getName());
        System.out.println("+You receive " + player.gainEXP(monster) + "EXP!");
        player.clearAllStatus();
        player.levelUp();
        if(initialLevel < player.getLevel()){
            System.out.println("+You are now Level " + player.getLevel());
        }
    }
    public void loseCombat(ConsoleToGUI consoleToGUI) {
        displayLoseCombat();
        isMonsterAlive = false;
        String choice = "";
        try {
            do {
                choice = consoleToGUI.getNextInput();
                if (!choice.matches("^[1-7]$")) {
                    System.out.println("Invalid input.");
                }
            } while (!choice.matches("^[1-7]$"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void displayLoseCombat(){
        File file=new File("src\\main\\java\\um\\fop\\ASCII\\LosingScreen.txt");
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
		while(s.hasNextLine())
		{
			String line = s.nextLine();
			System.out.println(line);
		}
		s.close();
    }
    public void displayWinCombat(){
        File file=new File("src\\main\\java\\um\\fop\\ASCII\\WinningScreen.txt");
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
		while(s.hasNextLine())
		{
			String line = s.nextLine();
            System.out.print("\t");
			System.out.println(line);
		}
		s.close();
    }
    public int checkWinLose(Entity player, Entity monster, ConsoleToGUI consoleToGUI) {
        if (player.getHP() <= 0) {
            loseCombat(consoleToGUI);
            isMonsterAlive = false;
            return -1;
        }
        if (monster.getHP() <= 0) {
            winCombat(player, monster);
            isMonsterAlive = false;
            return 1;
        }
        return 0;
    }
    public static Entity spawnRandom(Entity player){
        Random r = new Random();
        int rand = r.nextInt(100);
        if(rand < 27){
            return new Goblin(player);
        } else if (rand < 54){
            return new Orc(player);
        } else if (rand < 81){
            return new Skeleton(player);
        } else if(rand < 90){
            return new Witch(player);
        } else {
            return new Harpy(player);
        }
    }
}
