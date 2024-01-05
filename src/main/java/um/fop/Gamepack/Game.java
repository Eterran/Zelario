package Gamepack;

import java.util.Scanner;

import Entitypack.*;
import Entitypack.Monsterpack.*;
import Mappack.RandomMonsterMap;

import UIpack.ColorAttributes;
import UIpack.ConsoleToGUI;
import CombatMenu.CombatMenu;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;



public class Game {
    public Game(Entity player) {
        turn = true;
        isMonsterAlive = true;
        initialLevel = player.getLevel();
    }

    int initialLevel;
    boolean isMonsterAlive = true;
    boolean turn = true;

    public void beginCombat(Entity player, Entity monster, JTextPane textPane, ConsoleToGUI consoleToGUI,
            JFrame frame, JScrollPane scrollPane) {
        frame.setVisible(true);
        textPane.setText("");

        System.out.println("You entered a combat with a " + monster.getName());

        turn = true;
        isMonsterAlive = true;
        initialLevel = player.getLevel();
        while (isMonsterAlive) {
            System.out.print("+");
            for (int i = 0; i < 123; i++)
                System.out.print("-");
            System.out.println("+");
            System.out.println();
            try {
                CombatMenu.displayCombatMenu(player, monster, textPane, frame);
                Thread.sleep(100);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            int temp = checkWinLose(player, monster, textPane, consoleToGUI, scrollPane);
            if (temp == 1) {
                frame.setVisible(false);
                return;
            } else if (temp == -1) {
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
                            try {
                                clearInput(textPane);
                                StyledDocument doc = textPane.getStyledDocument();
                                doc.insertString(doc.getLength(), "You hit the ", ColorAttributes.WHITE);
                                doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                doc.insertString(doc.getLength(), " for ", ColorAttributes.WHITE);
                                doc.insertString(doc.getLength(),
                                String.format("%4d HP!\n", player.normalAttack(monster)), ColorAttributes.ORANGE);
                            } catch (BadLocationException e) {
                                e.printStackTrace();
                            }
                            endTurn();
                            break;
                        case "2":
                            try {
                                clearInput(textPane);
                                StyledDocument doc = textPane.getStyledDocument();
                                doc.insertString(doc.getLength(), "You brace youself for a hit.\n", ColorAttributes.WHITE);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            player.defend(monster);
                            endTurn();
                            break;
                        case "3":
                            clearInput(textPane);
                            Escape(frame);
                            endTurn();
                            return;
                        case "4":
                            clearInput(textPane);
                            player.healing();
                            if (player.getHP() < player.getMaxHP()) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "You have ", ColorAttributes.WHITE);
                                    doc.insertString(doc.getLength(), " healed 50 HP!\n", ColorAttributes.GREEN);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "You have ", ColorAttributes.WHITE);
                                    doc.insertString(doc.getLength(), " healed ", ColorAttributes.GREEN);
                                    doc.insertString(doc.getLength(),
                                            Integer.toString(50 - (player.getHP() - player.getMaxHP())),
                                            ColorAttributes.GREEN);
                                    doc.insertString(doc.getLength(), " HP!\n", ColorAttributes.GREEN);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                player.setHP(player.getHP());
                            }
                            endTurn();
                            break;
                        case "5":
                            if (!player.isSkill1Unlocked) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "You have not unlocked this skill yet!\n",
                                            ColorAttributes.GRAY);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                
                                break;
                            }

                            if (player.getCDSkill1() == 0 && player.checkMana(player.getSkill1Mp())) { // cd remain 4
                                if (!player.isSilenced) {
                                    clearInput(textPane);
                                    player.useSkill1(monster);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP)) {
                                        try {
                                            StyledDocument doc = textPane.getStyledDocument();
                                            doc.insertString(doc.getLength(), "You have used ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), player.getSkillOneName().replace("\t", ""),
                                                    ColorAttributes.ORANGE);
                                            doc.insertString(doc.getLength(), " and hit ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                            doc.insertString(doc.getLength(), " for ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(),
                                                    Integer.toString((monsterPreviousHP - monster.getHP())),
                                                    ColorAttributes.RED);
                                            doc.insertString(doc.getLength(), " HP!\n", ColorAttributes.RED);

                                        } catch (BadLocationException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            clearInput(textPane);
                                            StyledDocument doc = textPane.getStyledDocument();
                                            doc.insertString(doc.getLength(), "You have used ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), player.getSkillOneName().replace("\t", ""),
                                                    ColorAttributes.ORANGE);
                                            doc.insertString(doc.getLength(), " and ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(),
                                                    String.format("buff yourself for %s ATK!\n", player.getSkill1()),
                                                    ColorAttributes.CYAN);

                                        } catch (BadLocationException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    endTurn();
                                } else {
                                    try {
                                        StyledDocument doc = textPane.getStyledDocument();
                                        doc.insertString(doc.getLength(), "You are silenced!\n", ColorAttributes.MAGENTA);
                                        
                                    } catch (BadLocationException e) {
                                        e.printStackTrace();
                                    }
                                }

                            } else {
                                // System.out.println(player.getMP());//chechking
                                // System.out.println(player.getSkill1Mp());//chechking
                                // System.out.println(player.getCDSkill1());//checking current cd
                                if (player.getMP() < player.getSkill1Mp()) {
                                    try {
                                        StyledDocument doc = textPane.getStyledDocument();
                                        doc.insertString(doc.getLength(), "You do not have enough mana!\n",
                                                ColorAttributes.ORANGE);
                                        
                                    } catch (BadLocationException e) {
                                        e.printStackTrace();
                                    }
                                } else if (player.getCDSkill1() != 0) {
                                    try {
                                        StyledDocument doc = textPane.getStyledDocument();
                                        doc.insertString(doc.getLength(), "Your skill is not ready yet!\n",
                                                ColorAttributes.ORANGE);
                                        
                                    } catch (BadLocationException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            break;
                        case "6":
                            if (!player.isSkill2Unlocked) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "You have not unlocked this skill yet!\n",
                                            ColorAttributes.GRAY);
                                    
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            if (player.getCDSkill2() == 0 && player.checkMana(player.getSkill1Mp())) {
                                if (!player.isSilenced) {
                                    clearInput(textPane);
                                    player.useSkill2(monster);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP)) {
                                        try {
                                            StyledDocument doc = textPane.getStyledDocument();
                                            doc.insertString(doc.getLength(), "You have used ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), player.getSkillTwoName().replace("\t", ""),
                                                    ColorAttributes.ORANGE);
                                            doc.insertString(doc.getLength(), " and hit ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                            doc.insertString(doc.getLength(), " for ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(),
                                                    Integer.toString((monsterPreviousHP - monster.getHP())),
                                                    ColorAttributes.RED);
                                            doc.insertString(doc.getLength(), " HP!\n", ColorAttributes.RED);

                                        } catch (BadLocationException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            clearInput(textPane);
                                            StyledDocument doc = textPane.getStyledDocument();
                                            doc.insertString(doc.getLength(), "You have used ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), player.getSkillTwoName().replace("\t", ""),
                                                    ColorAttributes.ORANGE);
                                            doc.insertString(doc.getLength(), " and ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(),
                                                    String.format("buff yourself for %s ATK!\n", player.getSkill1()),
                                                    ColorAttributes.CYAN);
                                        } catch (BadLocationException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    endTurn();
                                } else {
                                    try {
                                        StyledDocument doc = textPane.getStyledDocument();
                                        doc.insertString(doc.getLength(), "You are silenced!\n", ColorAttributes.MAGENTA);
                                    } catch (BadLocationException e) {
                                        e.printStackTrace();
                                    }
                                    
                                }
                            } else {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "Your skill is not ready yet!\n",
                                            ColorAttributes.ORANGE);
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                
                            }
                            break;
                        case "7":
                            if (!player.isSkill3Unlocked) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "You have not unlocked this skill yet!\n",
                                            ColorAttributes.GRAY);
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                
                                break;
                            }
                            if (player.getCDSkill3() == 0 && player.checkMana(player.getSkill1Mp())) {
                                if (!player.isSilenced) {
                                    player.useSkill3(monster);
                                    clearInput(textPane);
                                    if (monster.checkMonsterHPChange(monsterPreviousHP)) {
                                        try {
                                            StyledDocument doc = textPane.getStyledDocument();
                                            doc.insertString(doc.getLength(), "You have used ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(),player.getSkillThreeName().replace("\t", ""),
                                                    ColorAttributes.ORANGE);
                                            doc.insertString(doc.getLength(), " and hit ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                            doc.insertString(doc.getLength(), " for ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(),
                                                    Integer.toString((monsterPreviousHP - monster.getHP())),
                                                    ColorAttributes.RED);
                                            doc.insertString(doc.getLength(), " HP!\n", ColorAttributes.RED);

                                        } catch (BadLocationException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            clearInput(textPane);
                                            StyledDocument doc = textPane.getStyledDocument();
                                            doc.insertString(doc.getLength(), "You have used ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), player.getSkillThreeName().replace("\t", ""),
                                                    ColorAttributes.ORANGE);
                                            doc.insertString(doc.getLength(), " and ", ColorAttributes.WHITE);
                                            doc.insertString(doc.getLength(), String.format("buff yourself for %s ATK!\n", player.getSkill3()),ColorAttributes.CYAN);

                                        } catch (BadLocationException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    endTurn();
                                } else {
                                    try {
                                        StyledDocument doc = textPane.getStyledDocument();
                                        doc.insertString(doc.getLength(), "You are silenced, you try to speak but no sound escapes your mouth!\n", ColorAttributes.MAGENTA);
                                    } catch (BadLocationException e) {
                                        e.printStackTrace();
                                    }
                                    
                                }
                            } else {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "Your skill is not ready yet!\n",
                                            ColorAttributes.ORANGE);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                
                            }
                            break;
                        default:
                            System.out.println("Invalid input");
                            
                    }
                }
            } else if (player.isFrozen) {
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "You tried to move but you are frozen in place!\n", ColorAttributes.MAGENTA);

                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                endTurn();
            } else if (player.isStunned) {
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "You tried to move but you are stunned!\n", ColorAttributes.MAGENTA);

                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                endTurn();
            }
            temp = checkWinLose(player, monster, textPane, consoleToGUI, scrollPane);
            if (temp == 1) {
                frame.setVisible(false);
                return;
            } else if (temp == -1) {
                return;
            }
            player.applyEffects(textPane);
            monster.applyEffects(textPane);
            if (!monster.isFrozen && !monster.isStunned) {
                if (!turn && isMonsterAlive) {
                    Random r = new Random();
                    int enemyChoice = r.nextInt(99);
                    if (monster.getSkill1() == -1) {
                        try {
                            StyledDocument doc = textPane.getStyledDocument();
                            doc.insertString(doc.getLength(), "\n", ColorAttributes.MAGENTA);
                            doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                            doc.insertString(doc.getLength(), " hits you for ", ColorAttributes.WHITE);
                            doc.insertString(doc.getLength(), Integer.toString((monster.normalAttack(player))),
                                    ColorAttributes.MAGENTA);
                            doc.insertString(doc.getLength(), " HP!\n", ColorAttributes.MAGENTA);

                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                        endTurn();
                    } else if (monster.getSkill1() != -1) {
                        if (enemyChoice < 40) {
                            try {
                                StyledDocument doc = textPane.getStyledDocument();
                                doc.insertString(doc.getLength(), "\n", ColorAttributes.MAGENTA);
                                doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                doc.insertString(doc.getLength(), " hits you for ", ColorAttributes.WHITE);
                                doc.insertString(doc.getLength(), Integer.toString((monster.normalAttack(player))),
                                        ColorAttributes.MAGENTA);
                                doc.insertString(doc.getLength(), " HP!\n", ColorAttributes.MAGENTA);

                            } catch (BadLocationException e) {
                                e.printStackTrace();
                            }
                            endTurn();
                        } else if (enemyChoice < 70) {
                            if (monster.getCDSkill1() == 0 && monster.checkMana(monster.getSkill1Mp())) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "\n", ColorAttributes.MAGENTA);
                                    doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                    doc.insertString(doc.getLength(), " used ", ColorAttributes.WHITE);
                                    doc.insertString(doc.getLength(), monster.getSkillOneName(), ColorAttributes.PINK);
                                    doc.insertString(doc.getLength(), " and hits you for ", ColorAttributes.WHITE);
                                    doc.insertString(doc.getLength(),
                                            String.format(" %d HP!\n", monster.useSkill1(player)), ColorAttributes.RED);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            }

                            else {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "\n", ColorAttributes.MAGENTA);
                                    doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                    doc.insertString(doc.getLength(), " and hits you for ", ColorAttributes.MAGENTA);
                                    doc.insertString(doc.getLength(), Integer.toString((monster.normalAttack(player))),
                                            ColorAttributes.MAGENTA);
                                    doc.insertString(doc.getLength(), " HP!\n", ColorAttributes.MAGENTA);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            }

                            endTurn();
                        } else if (enemyChoice < 100) {
                            if (monster.getCDSkill2() == 0) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "\n", ColorAttributes.MAGENTA);
                                    doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                    doc.insertString(doc.getLength(), " used ", ColorAttributes.WHITE);
                                    doc.insertString(doc.getLength(), monster.getSkillTwoName(), ColorAttributes.PINK);
                                    doc.insertString(doc.getLength(), " and hits you for ", ColorAttributes.WHITE);
                                    doc.insertString(doc.getLength(),
                                            String.format(" %d HP!\n", monster.useSkill2(player)), ColorAttributes.RED);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            }

                            else {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "\n", ColorAttributes.MAGENTA);
                                    doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                                    doc.insertString(doc.getLength(), " and hits you for ", ColorAttributes.MAGENTA);
                                    doc.insertString(doc.getLength(), Integer.toString((monster.normalAttack(player))),
                                            ColorAttributes.MAGENTA);
                                    doc.insertString(doc.getLength(), " HP!\n", ColorAttributes.MAGENTA);

                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            }

                            endTurn();
                        }
                    }
                }
            } else if (monster.isFrozen) {
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "\n", ColorAttributes.MAGENTA);
                    doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                    doc.insertString(doc.getLength(), " is frozen and cannot move! \n", ColorAttributes.CYAN);

                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

                endTurn();
            } else if (monster.isStunned) {
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "\n ", ColorAttributes.MAGENTA);
                    doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
                    doc.insertString(doc.getLength(), " is stunned and cannot move! \n", ColorAttributes.CYAN);

                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

                endTurn();
            }
            CDandStatus(player, monster, textPane);
        }
    }

    public void CDandStatus(Entity player, Entity monster, JTextPane textPane) {
        player.CDDecrement();
        monster.CDDecrement();
        player.applyEffects(textPane);
        monster.applyEffects(textPane);
        player.tickStatus();
        monster.tickStatus();
        player.recoverMana();
        monster.recoverMana();
    }

    public void endTurn() {
        turn = !turn;
    }

    public void winCombat(Entity player, Entity monster, JTextPane textPane, ConsoleToGUI consoleToGUI, JScrollPane scrollPane) {
        displayWinCombat();
        isMonsterAlive = false;
        try {
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), "Congratulations! You have defeated ", ColorAttributes.YELLOW);
            doc.insertString(doc.getLength(), monster.getName(), ColorAttributes.RED);
            doc.insertString(doc.getLength(), "!\n", ColorAttributes.YELLOW);

            doc.insertString(doc.getLength(), "You have received  ", ColorAttributes.YELLOW);
            doc.insertString(doc.getLength(), Integer.toString(player.gainEXP(monster)), ColorAttributes.PINK);
            doc.insertString(doc.getLength(), "EXP! \n", ColorAttributes.PINK);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        player.clearAllStatus();
        player.levelUp();
        if (initialLevel < player.getLevel()) {
            try {
                StyledDocument doc = textPane.getStyledDocument();
                doc.insertString(doc.getLength(), "You are now ", ColorAttributes.YELLOW);
                doc.insertString(doc.getLength(), String.format("Level %d", player.getLevel()), ColorAttributes.PINK);
                doc.insertString(doc.getLength(), "! \n", ColorAttributes.YELLOW);

            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        try {
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), "Press Enter to continue.\n", ColorAttributes.DARK_GRAY);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        String choice = "";
        try {
            choice = consoleToGUI.getNextInput();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        consoleToGUI.scrollToBottom(scrollPane);
    }

    public void loseCombat(ConsoleToGUI consoleToGUI) {
        displayLoseCombat();
        isMonsterAlive = false;
        String choice = "";
        try {
            do {
                choice = consoleToGUI.getNextInput();
                if (!choice.matches("^[1-2]$")) {
                    System.out.println("Invalid input.");
                }
            } while (!choice.matches("^[1-2]$"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(choice.equals("1")){

        } else {
            
        }
    }

    public void displayLoseCombat() {
        File file = new File("src\\main\\java\\um\\fop\\ASCII\\LosingScreen.txt");
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
            System.out.println(line);
        }
        s.close();
    }

    public void displayWinCombat() {
        File file = new File("src\\main\\java\\um\\fop\\ASCII\\WinningScreen.txt");
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
            System.out.print("\t");
            System.out.println(line);
        }
        s.close();
    }

    public int checkWinLose(Entity player, Entity monster, JTextPane textPane, ConsoleToGUI consoleToGUI, JScrollPane scrollPane) {
        if (player.getHP() <= 0) {
            loseCombat(consoleToGUI);
            isMonsterAlive = false;
            RandomMonsterMap.getMapFrame().setVisible(true);
            return -1;
        }
        if (monster.getHP() <= 0) {
            winCombat(player, monster, textPane, consoleToGUI, scrollPane);
            isMonsterAlive = false;
            RandomMonsterMap.getMapFrame().setVisible(true);
            return 1;
        }
        return 0;
    }
    public void Escape(JFrame frame){
        Random r = new Random();
        int escapeChance = r.nextInt(99);
        if(escapeChance < 50){
            isMonsterAlive = false;
            RandomMonsterMap.getMapFrame().setVisible(true);
            frame.setVisible(false);
        } else {
            System.out.println("You failed to escape!");
        }
    }
    public static Entity spawnRandom(Entity player) {
        Random r = new Random();
        int rand = r.nextInt(100);
        if (rand < 27) {
            return new Goblin(player);
        } else if (rand < 54) {
            return new Orc(player);
        } else if (rand < 81) {
            return new Skeleton(player);
        } else if (rand < 90) {
            return new Witch(player);
        } else {
            return new Harpy(player);
        }
    }
    public void clearInput(JTextPane textPane){
            textPane.setText("");
    }
}
