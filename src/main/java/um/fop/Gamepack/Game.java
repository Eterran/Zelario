package Gamepack;
import Entitypack.Playerpack.Rogue;


import java.util.Scanner;
import Entitypack.*;
import Entitypack.Monsterpack.*;
import CombatMenu.CombatMenu;

import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Game extends JFrame {
    private JLabel combatStatusLabel;
    int initialLevel;
    boolean isMonsterAlive = true;
    boolean turn = true;
    private JLabel statusLabel;


    public Game(Entity player) {
        // 设置窗口的属性
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(40, 40);
        this.setLayout(new BorderLayout());

        // 创建并设置combatStatusLabel
        combatStatusLabel = new JLabel("Ready to fight!");
        this.add(combatStatusLabel, BorderLayout.SOUTH);

        turn = true;
        isMonsterAlive = true;
        initialLevel = player.getLevel();

        this.setVisible(true);
    }
    private void updateCombatStatus(String text) {
        SwingUtilities.invokeLater(() -> {
            combatStatusLabel.setText(text);
        });
    }

    public void beginCombat(Entity player, Entity monster) {
        statusLabel = new JLabel();
        add(statusLabel);
        updateCombatStatus("You entered a combat with a " + monster.getName());
        Scanner s = new Scanner(System.in);
        turn = true;
        isMonsterAlive = true;
        initialLevel = player.getLevel();
        while (isMonsterAlive) {
            try{
                CombatMenu.displayCombatMenu(player,monster);
            }catch(IOException e){
                e.printStackTrace();
            }
            if(checkWinLose(player, monster) != 0){
                return;
            }

            if (!player.isFrozen && !player.isStunned) {
                while (turn) {
                    int choice;
                    while (true) {
                        try {
                            choice = Integer.parseInt(s.nextLine());
                            break;
                        } catch (Exception e) {
                            updateCombatStatus("Invalid input");
                            s.nextLine();
                        }
                    }
                    int monsterPreviousHP = monster.getHP();
                    switch (choice) {
                        case 1:
                            updateCombatStatus("You hit the " + monster.getName() + " for "
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
                                updateCombatStatus("Invalid input");
                                break;
                            }

                            if (player.getCDSkill1() == 0 && player.checkMana(player.getSkill1Mp())) { //cd remain 4
                                if (!player.isSilenced) {
                                    player.useSkill1(monster);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP))
                                        updateCombatStatus("You used " + player.getSkillOneName()
                                                + " and hit " + monster.getName() + " for "
                                                + (monsterPreviousHP - monster.getHP()) + "HP!");
                                    else
                                        updateCombatStatus("You used " + player.getSkillOneName()
                                                + " and buff youself for " + player.getSkill1() + "ATK!");
                                    endTurn();
                                } else {
                                    updateCombatStatus("You are silenced!");
                                }
                            } else {
                                updateCombatStatus("Current MP: " + player.getMP()); // //chechking 将int转换为String的隐式转换
                                updateCombatStatus("Skill 1 MP Cost: " + player.getSkill1Mp()); // //chechking
                                updateCombatStatus("Current Skill 1 Cooldown: " + player.getCDSkill1()); // checking current cd
                                updateCombatStatus("Your skill is not ready yet!");
                            }
                            break;
                        case 6:
                            if (!player.isSkill2Unlocked) {
                                updateCombatStatus("Invalid input");
                                break;
                            }
                            if (player.getCDSkill2() == 0 && player.checkMana(player.getSkill1Mp())) {
                                if (!player.isSilenced) {
                                    player.useSkill2(monster);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP))
                                        updateCombatStatus("You used " + player.getSkillTwoName()
                                                + " and hit " + monster.getName() + " for "
                                                + (monsterPreviousHP - monster.getHP()) + "HP!");
                                    else
                                        updateCombatStatus("You used " + player.getSkillTwoName()
                                                + " and buff youself for " + player.getSkill2() + "ATK!");
                                    endTurn();
                                } else {
                                    updateCombatStatus("You are silenced!");
                                }
                            } else {
                                updateCombatStatus("Your skill is not ready yet!");
                            }
                            break;
                        case 7:
                            if (!player.isSkill3Unlocked) {
                                updateCombatStatus("Invalid input");
                                break;
                            }
                            if (player.getCDSkill3() == 0 && player.checkMana(player.getSkill1Mp())) {
                                if (!player.isSilenced) {
                                    player.useSkill3(monster);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP))
                                        updateCombatStatus("You used " + player.getSkillThreeName()
                                                + " and hit " + monster.getName() + " for "
                                                + (monsterPreviousHP - monster.getHP()) + "HP!");
                                    else
                                        updateCombatStatus("You used " + player.getSkillThreeName()
                                                + " and buff youself for " + player.getSkill1() + "ATK!");
                                    endTurn();
                                } else {
                                    updateCombatStatus("You are silenced!");
                                }
                            } else {
                                updateCombatStatus("Your skill is not ready yet!");
                            }
                            break;
                        default:
                            updateCombatStatus("Invalid input");
                    }
                }
            } else if(player.isFrozen) {
                updateCombatStatus("You are frozen!");
                endTurn();
            } else if(player.isStunned) {
                updateCombatStatus("You are stunned!");
                endTurn();
            }
            if(checkWinLose(player, monster) != 0){
                return;
            }
            if (!monster.isFrozen && !monster.isStunned) {
                if (!turn && isMonsterAlive) {
                    Random r = new Random();
                    int enemyChoice = r.nextInt(99);
                    if (monster.getSkill1() == -1 && monster.checkMana(monster.getSkill1Mp())) {
                        updateCombatStatus(monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                        endTurn();
                    } else if (monster.getSkill2() != -1 && monster.checkMana(monster.getSkill1Mp())) {
                        if (enemyChoice < 40) {
                            updateCombatStatus(monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                            endTurn();
                        } else if (enemyChoice < 70) {
                            if (monster.getCDSkill1() == 0)
                                monster.useSkill1(player);
                            else
                                updateCombatStatus(
                                        monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                            endTurn();
                        } else if (enemyChoice < 100) {
                            if (monster.getCDSkill2() == 0)
                                monster.useSkill2(player);
                            else
                                updateCombatStatus(
                                        monster.getName() + " hits you for " + monster.normalAttack(player) + "HP!");
                            endTurn();
                        }
                    }
                }
            } else if(monster.isFrozen){
                updateCombatStatus(
                        monster.getName() + " is frozen and cannot move! ");
                endTurn();
            } else if(monster.isStunned){
                updateCombatStatus(
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
        updateCombatStatus("You have defeated " + monster.getName());
        updateCombatStatus("You receive " + player.gainEXP(monster) + "EXP!");
        player.clearAllStatus();
        player.levelUp();
        if(initialLevel < player.getLevel()){
            updateCombatStatus("You are now Level " + player.getLevel());
        }
        isMonsterAlive = false;
    }
    public void loseCombat() {
        // displayLoseCombat(); // how to handle losing?
        updateCombatStatus("You lost!");
        isMonsterAlive = false;
    }
    public int checkWinLose(Entity player, Entity monster) {
        if (player.getHP() <= 0) {
            loseCombat();
            return -1;
        }
        if (monster.getHP() <= 0) {
            winCombat(player, monster);
            return 1;
        }
        return 0;
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
    public void updateStatusLabel(String message) {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText(message);
        });
    }
    public void someMethod() {
        updateStatusLabel("Some message");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Entity player = new Rogue(); // 假设你有一个名为Rogue的Entity子类
            Game gameFrame = new Game(player);
        });
    }
}

