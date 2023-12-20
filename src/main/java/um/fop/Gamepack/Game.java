package Gamepack;

import java.util.Scanner;
import Entitypack.*;
import Entitypack.Monsterpack.*;

import java.util.Random;

public class Game {
    public Game(Entity player) {
        turn = true;
        isMonsterAlive = true;
        initialLevel = player.getLevel();
    }
    int initialLevel;
    boolean isMonsterAlive = true;
    boolean turn = true;

    public void beginCombat(Entity player, Entity monster) {
        System.out.println("You entered a combat with a " + monster.getName());
        Scanner s = new Scanner(System.in);
        turn = true;
        isMonsterAlive = true;
        initialLevel = player.getLevel();
        while (isMonsterAlive) {
            // displayCombatMenu();
            checkWinLose(player, monster);
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Escape");
            System.out.println("4. Heal");
            if (player.isSkill1Unlocked)
                System.out.println(
                        "5. " + player.getSkillOneName() + "        <" + player.getSkill1Description() + ">");
            if (player.isSkill2Unlocked)
                System.out.println(
                        "6. " + player.getSkillTwoName() + "        <" + player.getSkill2Description() + ">");
            if (player.isSkill3Unlocked)
                System.out.println(
                        "7. " + player.getSkillThreeName() + "        <" + player.getSkill3Description() + ">");
            if (!player.isFrozen && !player.isStunned) {
                while (turn) {
                    int choice;
                    while (true) {
                        try {
                            choice = Integer.parseInt(s.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                        }
                    }
                    int monsterPreviousHP = monster.getHP();
                    switch (choice) {
                        case 1:
                            System.out.println("You hit the " + monster.getName() + " for "
                                    + player.normalAttack(monster) + "HP!");
                            endTurn();
                            break;
                        case 2:
                            // player.defend(monster);
                            endTurn();
                            break;
                        case 3:
                            // Escape();
                            endTurn();
                            return;
                        case 4:
                            player.healing();
                            endTurn();
                            break;
                        case 5:
                            if (!player.isSkill1Unlocked) {
                                System.out.println("Invalid input");
                                break;
                            }
                            if (player.getCDSkill1() == 0) {
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
                                System.out.println("Your skill is not ready yet!");
                            }
                            break;
                        case 6:
                            if (!player.isSkill2Unlocked) {
                                System.out.println("Invalid input");
                                break;
                            }
                            if (player.getCDSkill2() == 0) {
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
                        case 7:
                            if (!player.isSkill3Unlocked) {
                                System.out.println("Invalid input");
                                break;
                            }
                            if (player.getCDSkill3() == 0) {
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
            checkWinLose(player, monster);
            if (!monster.isFrozen && !monster.isStunned) {
                if (!turn && isMonsterAlive) {
                    Random r = new Random();
                    int enemyChoice = r.nextInt(99);
                    if (monster.getSkill1() == -1) {
                        System.out.println(monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                        endTurn();
                    } else if (monster.getSkill2() != -1) {
                        if (enemyChoice < 40) {
                            System.out.println(monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                            endTurn();
                        } else if (enemyChoice < 70) {
                            if (monster.getCDSkill1() == 0)
                                monster.useSkill1(player);
                            else
                                System.out.println(
                                        monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                            endTurn();
                        } else if (enemyChoice < 100) {
                            if (monster.getCDSkill2() == 0)
                                monster.useSkill2(player);
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
    }
    public void endTurn() {
        turn = !turn;
    }
    public void winCombat(Entity player, Entity monster) {
        // displayWinCombat(player.gainEXP(monster)); // win screen?
        System.out.println("You have defeated " + monster.getName());
        System.out.println("You receive " + player.gainEXP(monster) + "EXP!");
        player.clearAllStatus();
        player.levelUp();
        if(initialLevel < player.getLevel()){
            System.out.println("You are now Level " + player.getLevel());
        }
        isMonsterAlive = false;
    }
    public void loseCombat() {
        // displayLoseCombat(); // how to handle losing?
        System.out.println("You lost!");
        isMonsterAlive = false;
    }
    public void checkWinLose(Entity player, Entity monster) {
        if (player.getHP() <= 0) {
            loseCombat();
            return;
        }
        if (monster.getHP() <= 0) {
            winCombat(player, monster);
            return;
        }
    }
    public Entity spawnRandom(){
        Random r = new Random();
        int rand = r.nextInt(100);
        if(rand < 27){
            return new Goblin();
        } else if (rand < 54){
            return new Orc();
        } else if (rand < 81){
            return new Skeleton();
        } else if(rand < 90){
            return new Witch();
        } else {
            return new Harpy();
        }
    }
}
