package CombatMenu;

import Entitypack.*;
import UIpack.ColorAttributes;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


public class CombatMenu {

	public static void displayIcon(Entity player, Entity monster, JTextPane textPane, JFrame frame) {

		File heroIconFile=null;
		File enemyIconFile=null;
		try {
			
			if(player.getName().equals("Archer"))
			{
				heroIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Archer Icon.txt");
			}	
			if(player.getName().equals("Mage"))
				heroIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Mage Icon.txt");

			if(player.getName().equals("Paladin"))
				heroIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Paladin Icon.txt");
			
			if(player.getName().equals("Rogue"))
				heroIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Rogue Icon.txt");
			
			if(player.getName().equals("Warrior"))
				heroIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Warrior Icon.txt");

			if(monster.getName().equals("Dragon"))
				enemyIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Boss Icon.txt");

			if(monster.getName().equals("Goblin"))
				enemyIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Goblin Icon.txt");

			if(monster.getName().equals("Harpy"))
				enemyIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Harpy Icon.txt");

			if(monster.getName().equals("Orc"))
				enemyIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Orc Icon.txt");

			if(monster.getName().equals("Skeleton"))
				enemyIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Skeleton Icon.txt");

			if(monster.getName().equals("Witch"))
				enemyIconFile = new File("src\\main\\java\\um\\fop\\ASCII\\Witch Icon.txt");
			
			
			Scanner heroScanner = new Scanner(heroIconFile);
			Scanner enemyScanner = new Scanner(enemyIconFile);

			while (heroScanner.hasNextLine() || enemyScanner.hasNextLine()) {
				if (heroScanner.hasNextLine()) {
					String heroLine = heroScanner.nextLine();
					
					try {
						StyledDocument doc = textPane.getStyledDocument();
						doc.insertString(doc.getLength(), "\t", ColorAttributes.WHITE);
						doc.insertString(doc.getLength(), heroLine, ColorAttributes.YELLOW);
						
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					//System.out.print("\t" + heroLine);
				} else {
					System.out.print("\t"); // Print empty space if hero file is shorter
				}
				
				for (int i = 0; i < 40; i++) {
					System.out.print(" "); // Print 30 spaces between the two files
				}
				if (enemyScanner.hasNextLine()) {
					String enemyLine = enemyScanner.nextLine();
					try {
						StyledDocument doc = textPane.getStyledDocument();
						doc.insertString(doc.getLength(), "\t", ColorAttributes.WHITE);
						doc.insertString(doc.getLength(), enemyLine, ColorAttributes.RED);
						System.out.println();
						
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
					//System.out.println("\t" + enemyLine);
				} else {
					System.out.println(); // Move to the next line if enemy file is shorter
				}
			}
	
			heroScanner.close();
			enemyScanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void displayCombatMenu(Entity player, Entity monster, JTextPane textPane, JFrame frame) throws IOException {
		
		displayIcon(player,monster,textPane,frame);
		frame.setSize(1150, 800);

		System.out.print("+");

		int width = 123;

		for (int i = 0; i < width; i++)
			System.out.print("-");
		System.out.println("+");
		System.out.println();
		
		System.out.printf("%-60.8s"+"             ",player.getName());
		System.out.printf("%s%n",monster.getName());
		System.out.println("");

		try {
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), "Level  : ", ColorAttributes.YELLOW);
			doc.insertString(doc.getLength(), String.format("%3d", player.getLevel()), ColorAttributes.YELLOW);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

		System.out.println();

		HealthBar2 b = new HealthBar2(); // HPbar for hero
		b.HeroHealthBar(player,textPane);

		for (int i = 0; i < 11; i++)
			System.out.print(" ");

		System.out.print("|");
		for (int i = 0; i < 11; i++)
			System.out.print(" ");

		b.MonsterHealthBar(monster,textPane);

		System.out.println();// HPbar for monster

		ManaBar c = new ManaBar();
		c.HeroManaBar(player,textPane);
		for (int i = 0; i < 11; i++)
			System.out.print(" ");

		System.out.print("|");
		for (int i = 0; i < 11; i++)
			System.out.print(" ");

		c.MonsterManaBar(monster,textPane);
		System.out.println();
		System.out.println();
		System.out.print("+ ");
		for (int i = 0; i < width / 2; i++)
			System.out.print("- ");
		System.out.println("+");

		System.out.println();
		System.out.println(" 1. Normal Attack");
		System.out.println(" 2. Defend");
		System.out.println(" 3. Escape");
		System.out.println(" 4. Heal");

		if (player.getSkill1Unlocked()) {
			try {
				StyledDocument doc = textPane.getStyledDocument();
				doc.insertString(doc.getLength(), " 5. ", ColorAttributes.GREEN);
				doc.insertString(doc.getLength(), String.format("%3.20s", player.getSkillOneName()), ColorAttributes.ORANGE);			
				doc.insertString(doc.getLength(), "\t"+player.getSkill1Description(), ColorAttributes.CYAN);
				doc.insertString(doc.getLength(), "\n    \t\t\tRequires ", ColorAttributes.BLUE);
				doc.insertString(doc.getLength(), String.format("%3d", player.getSkill1Mp()), ColorAttributes.BLUE);			

				//doc.insertString(doc.getLength(), Integer.toString(player.getSkill1Mp()), ColorAttributes.BLUE);			
				doc.insertString(doc.getLength(), " mana", ColorAttributes.BLUE);
				doc.insertString(doc.getLength(), "\t\tCooldown: ", ColorAttributes.ORANGE);
				doc.insertString(doc.getLength(), Integer.toString(player.getCDSkill1()), ColorAttributes.ORANGE);	
				doc.insertString(doc.getLength(), "/", ColorAttributes.ORANGE);
				doc.insertString(doc.getLength(), Integer.toString(player.getMaxCDSkill1()), ColorAttributes.ORANGE);


			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		
		
		}

		else {
			try {
            StyledDocument doc = textPane.getStyledDocument();
			doc.insertString(doc.getLength(), String.format(" 5. %3.20s", player.getSkillOneName()), ColorAttributes.LIGHT_GRAY);
			doc.insertString(doc.getLength(), "\t*Locked*", ColorAttributes.LIGHT_GRAY);
		
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
			
		}

		if (player.getSkill2Unlocked()) {
			try {
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), "\n 6. ", ColorAttributes.GREEN);
			doc.insertString(doc.getLength(), String.format("%3.20s", player.getSkillTwoName()), ColorAttributes.ORANGE);			
			doc.insertString(doc.getLength(), "\t"+player.getSkill2Description(), ColorAttributes.CYAN);
			doc.insertString(doc.getLength(), "\n    \t\t\tRequires ", ColorAttributes.BLUE);
			doc.insertString(doc.getLength(), String.format("%3d", player.getSkill2Mp()), ColorAttributes.BLUE);			

			//doc.insertString(doc.getLength(), Integer.toString(player.getSkill2Mp()), ColorAttributes.BLUE);			
			doc.insertString(doc.getLength(), " mana", ColorAttributes.BLUE);
			doc.insertString(doc.getLength(), "\t\tCooldown: ", ColorAttributes.ORANGE);
			doc.insertString(doc.getLength(), Integer.toString(player.getCDSkill2()), ColorAttributes.ORANGE);	
			doc.insertString(doc.getLength(), "/", ColorAttributes.ORANGE);
			doc.insertString(doc.getLength(), Integer.toString(player.getMaxCDSkill2()), ColorAttributes.ORANGE);


        } catch (BadLocationException e) {
            e.printStackTrace();
        }
		
		
		}

		else {
			try {
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), String.format("\n 6. %3.20s", player.getSkillTwoName()), ColorAttributes.LIGHT_GRAY);
			doc.insertString(doc.getLength(), "\t*Locked*", ColorAttributes.LIGHT_GRAY);
		
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
		}

		if (player.getSkill3Unlocked()) {
			try {
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), "\n 7. ", ColorAttributes.GREEN);
			doc.insertString(doc.getLength(), String.format("%3.20s ", player.getSkillThreeName()), ColorAttributes.ORANGE);			
			doc.insertString(doc.getLength(), "\t"+player.getSkill3Description(), ColorAttributes.CYAN);
			doc.insertString(doc.getLength(), "\n    \t\t\tRequires ", ColorAttributes.BLUE);
			doc.insertString(doc.getLength(), String.format("%3d", player.getSkill3Mp()), ColorAttributes.BLUE);			

			//doc.insertString(doc.getLength(), Integer.toString(player.getSkill3Mp()), ColorAttributes.BLUE);			
			doc.insertString(doc.getLength(), " mana", ColorAttributes.BLUE);
			doc.insertString(doc.getLength(), "\t\tCooldown: ", ColorAttributes.ORANGE);
			doc.insertString(doc.getLength(), Integer.toString(player.getCDSkill3()), ColorAttributes.ORANGE);	
			doc.insertString(doc.getLength(), "/", ColorAttributes.ORANGE);
			doc.insertString(doc.getLength(), Integer.toString(player.getMaxCDSkill3()), ColorAttributes.ORANGE);


        } catch (BadLocationException e) {
            e.printStackTrace();
        }
		
		
		}

		else {
			try {
            StyledDocument doc = textPane.getStyledDocument();
           doc.insertString(doc.getLength(), String.format("\n 7. %3.20s", player.getSkillThreeName()), ColorAttributes.LIGHT_GRAY);
			doc.insertString(doc.getLength(), "\t*Locked*", ColorAttributes.LIGHT_GRAY);
		
		
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
		}
		System.out.println();
		System.out.print("+");
		width = 123;
		for (int i = 0; i < width; i++)
			System.out.print("-");
		System.out.println("+");
		System.out.print(">");

	}

}
