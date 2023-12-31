package PickCharacter;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import Gamepack.ConsoleToGUI;
import UIpack.ColorAttributes;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;

public class PickCharacter {
	public static int heroChoice = -1;

	public static void pickCharacterMenu(ConsoleToGUI consoleToGUI, JTextField userInputField, JTextPane textPane) throws FileNotFoundException {
		File file = new File("Zelario-main\\src\\main\\java\\um\\fop\\ASCII\\Menu Logo ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			try {
				StyledDocument doc = textPane.getStyledDocument();
				doc.insertString(doc.getLength(), data, ColorAttributes.GREEN);
				System.out.println();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		sc.close();
		System.out.print("+");

		int width = 86;
		for (int a = 0; a < width / 2; a++)
			System.out.print("- ");
		System.out.println("+");

		for (int a = 0; a < 30; a++)
			System.out.print(" ");
		System.out.println("-- Pick your class --");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" 1. Rogue");
		System.out.println(" 2. Warrior");
		System.out.println(" 3. Archer");
		System.out.println(" 4. Mage");
		System.out.println(" 5. Paladin");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");
		System.out.print(">");


		String input = "";
		try {
			do {
				input = consoleToGUI.getNextInput();
				if (!input.matches("^[1-5]$")) {
					System.out.println("Invalid input.");
				}
			} while (!input.matches("^[1-5]$"));
        } catch (InterruptedException e) {
            
        }
		
		if (input.equals("1")) {
			PickRogue(consoleToGUI, userInputField, textPane);
		} else if (input.equals("2")) {
			PickWarrior(consoleToGUI, userInputField, textPane);
		} else if (input.equals("3")) {
			PickArcher(consoleToGUI, userInputField, textPane);
		} else if (input.equals("4")) {
			PickMage(consoleToGUI, userInputField, textPane);
		} else if (input.equals("5")) {
			PickPaladin(consoleToGUI, userInputField, textPane);
		}
	}

	public static void PickRogue(ConsoleToGUI consoleToGUI, JTextField userInputField, JTextPane textPane) throws FileNotFoundException {
		File file = new File("Zelario-main\\src\\main\\java\\um\\fop\\ASCII\\Rogue ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			try {
				StyledDocument doc = textPane.getStyledDocument();
				doc.insertString(doc.getLength(), data, ColorAttributes.ORANGE);
				System.out.println();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}

		System.out.print("+");

		int width = 86;
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		for (int a = 0; a < 38; a++)
			System.out.print(" ");
		System.out.println("-- Rogue -- ");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Skills:");
		System.out.println(" ------");
		System.out.println();

		System.out.println(" Smite     : Slash a hit to monster");
		System.out.println(" Backstab  : The Rogue sneaks behind the target, delivering a devastating backstab, ");
		System.out.println(" 	     causing extra damage and stunning the enemy for 1 round.");
		System.out.println(" Shadowstep: The Rogue can evade the next spell or attack from the enemies.");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.print(">");

		String choice = "";
		try {
			do {
				choice = consoleToGUI.getNextInput();
				if (!choice.matches("^[1-2]$")) {
					System.out.println("Invalid input.");
				}
			} while (!choice.matches("^[1-2]$"));
        } catch (InterruptedException e) {
            
        }
		if (choice.equals("1")) {
			heroChoice = 1;
		}
		else if (choice.equals("2")) {
			PickCharacter.pickCharacterMenu(consoleToGUI, userInputField, textPane);
		}
	}

	public static void PickArcher(ConsoleToGUI consoleToGUI, JTextField userInputField, JTextPane textPane) throws FileNotFoundException {
		File file = new File("Zelario-main\\src\\main\\java\\um\\fop\\ASCII\\Archer ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			try {
				StyledDocument doc = textPane.getStyledDocument();
				doc.insertString(doc.getLength(), data, ColorAttributes.CYAN);
				System.out.println();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}

		System.out.print("+");

		int width = 86;
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		for (int a = 0; a < 38; a++)
			System.out.print(" ");
		System.out.println("-- Archer -- ");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Skills:");
		System.out.println(" ------");
		System.out.println();

		System.out.println(" Precise    : Buff physical attack for 2 rounds.");
		System.out.println(" Rapid Arrow: Fires a flurry of arrows at the target, dealing moderate damage.");
		System.out.println(" Trick Shot : Executes a precise shot that can pierce the enemies, ");
		System.out.println("	      causing heavy damage to the enemy.");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.print(">");

		String choice = "";
		try {
			do {
				choice = consoleToGUI.getNextInput();
				if (!choice.matches("^[1-2]$")) {
					System.out.println("Invalid input.");
				}
			} while (!choice.matches("^[1-2]$"));
        } catch (InterruptedException e) {
            
        }
		if (choice.equals("1")) {
			heroChoice = 3;
		}
		else if (choice.equals("2")) {
			PickCharacter.pickCharacterMenu(consoleToGUI, userInputField, textPane);
		}
	}

	public static void PickMage(ConsoleToGUI consoleToGUI, JTextField userInputField, JTextPane textPane) throws FileNotFoundException {
		File file = new File("Zelario-main\\src\\main\\java\\um\\fop\\ASCII\\Mage ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
		try {
				StyledDocument doc = textPane.getStyledDocument();
				doc.insertString(doc.getLength(), data, ColorAttributes.MAGENTA);
				System.out.println();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}		}

		System.out.print("+");

		int width = 86;
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		for (int a = 0; a < 38; a++)
			System.out.print(" ");
		System.out.println("-- Mage -- ");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Skills:");
		System.out.println(" ------");
		System.out.println();

		System.out.println(" Poison  : Monster will be poisoned for 2 rounds.");
		System.out.println(" Fireball: Hurls a fiery projectile at the target, dealing moderate fire damage.");
		System.out.println(" Frost   : The Mage releases a burst of frost, freezing nearby enemies in place ");
		System.out.println("           for 2 rounds.");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.print(">");

		String choice = "";
		try {
			do {
				choice = consoleToGUI.getNextInput();
				if (!choice.matches("^[1-2]$")) {
					System.out.println("Invalid input.");
				}
			} while (!choice.matches("^[1-2]$"));
       		 } catch (InterruptedException e) {
            
       		 }
		if (choice.equals("1")) {
			heroChoice = 4;
		}
		else if (choice.equals("2")) {
			PickCharacter.pickCharacterMenu(consoleToGUI, userInputField, textPane);
		}
	}

	public static void PickWarrior(ConsoleToGUI consoleToGUI, JTextField userInputField, JTextPane textPane) throws FileNotFoundException {
		File file = new File("Zelario-main\\src\\main\\java\\um\\fop\\ASCII\\Warrior ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
		try {
				StyledDocument doc = textPane.getStyledDocument();
				doc.insertString(doc.getLength(), data, ColorAttributes.RED);
				System.out.println();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}		}

		System.out.print("+");

		int width = 86;
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		for (int a = 0; a < 38; a++)
			System.out.print(" ");
		System.out.println("-- Warrior -- ");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Skills:");
		System.out.println(" ------");
		System.out.println();

		System.out.println(" Cha Cha Cha   : Slash a hit to monster.");
		System.out.println(" Furious Strike: Unleashes a powerful attack, dealing heavy damage to the target.");
		System.out.println(" Shield Wall   : The Warrior creates an impenetrable barrier with their shield,");
		System.out.println(" 	         reducing incoming damage for 3 rounds.");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.print(">");

		String choice = "";
		try {
			do {
				choice = consoleToGUI.getNextInput();
				if (!choice.matches("^[1-2]$")) {
					System.out.println("Invalid input.");
				}
			} while (!choice.matches("^[1-2]$"));
	        } catch (InterruptedException e) {
	            
	        }
		if (choice.equals("1")) {
			heroChoice = 2;
		}
		else if (choice.equals("2")) {
			PickCharacter.pickCharacterMenu(consoleToGUI, userInputField, textPane);
		}
	}

	public static void PickPaladin(ConsoleToGUI consoleToGUI, JTextField userInputField, JTextPane textPane) throws FileNotFoundException {
		File file = new File("Zelario-main\\src\\main\\java\\um\\fop\\ASCII\\Paladin ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			try {
				StyledDocument doc = textPane.getStyledDocument();
				doc.insertString(doc.getLength(), data, ColorAttributes.PINK);
				System.out.println();
			} catch (BadLocationException e) {
				e.printStackTrace();
			}		}

		System.out.print("+");

		int width = 86;
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		for (int a = 0; a < 38; a++)
			System.out.print(" ");
		System.out.println("-- Paladin -- ");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Skills:");
		System.out.println(" ------");
		System.out.println();

		System.out.println(" Rage         : Paladin attack increased for 3 rounds.");
		System.out.println(" Holy Smite   : Smashes the target with divine light, dealing damage and healing the ");
		System.out.println("	        Paladin for a portion of the damage dealt.");
		System.out.println(" Divine Shield: Creates a protective barrier around the Paladin, rendering them ");
		System.out.println("	        immune to damage for 2 rounds.");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.print(">");

		String choice = "";
		try {
			do {
				choice = consoleToGUI.getNextInput();
				if (!choice.matches("^[1-2]$")) {
					System.out.println("Invalid input.");
				}
			} while (!choice.matches("^[1-2]$"));
	        } catch (InterruptedException e) {
	            
	        }
		if (choice.equals("1")) {
			heroChoice = 5;
		}
		else if (choice.equals("2")) {
			PickCharacter.pickCharacterMenu(consoleToGUI, userInputField, textPane);
		}
	}
	public int getHeroChoice() {
		return heroChoice;
	}
	public void setHeroChoice(int a) {
		heroChoice = a;
		return;
	}
}
