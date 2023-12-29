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
		File file = new File("src\\main\\java\\um\\fop\\ASCII\\Menu Logo ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			System.out.println(data);
		}
		sc.close();
		System.out.print("+");

		try {
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), "Red Text\n", ColorAttributes.RED);
            doc.insertString(doc.getLength(), "White Text\n", ColorAttributes.WHITE);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

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
		System.out.println("");
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
		File file = new File("src\\main\\java\\um\\fop\\ASCII\\Rogue ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			System.out.println(data);
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

		System.out.println(" Smite:");
		System.out.println(" Backstab:");
		System.out.println(" Shadowstep:");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.println("");
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
		File file = new File("src\\main\\java\\um\\fop\\ASCII\\Archer ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			System.out.print("      ");

			System.out.println(data);
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

		System.out.println(" Precise: ");
		System.out.println(" Rapid Arrow:");
		System.out.println(" Trick Shot:");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.println("");
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
		File file = new File("src\\main\\java\\um\\fop\\ASCII\\Mage ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			System.out.println(data);
		}

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

		System.out.println(" Poison: ");
		System.out.println(" Fireball:");
		System.out.println(" Frost:");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.println("");
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
		File file = new File("src\\main\\java\\um\\fop\\ASCII\\Warrior ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			System.out.println(data);
		}

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

		System.out.println(" Cha Cha Cha:");
		System.out.println(" Furious Strike:");
		System.out.println(" Shield Wall:");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.println("");
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
		File file = new File("src\\main\\java\\um\\fop\\ASCII\\Paladin ASCII.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			String data = sc.nextLine();
			System.out.println(data);
		}

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

		System.out.println(" Rage:");
		System.out.println(" Holy Smite:");
		System.out.println(" Divine Shield:");

		System.out.print("+");
		for (int a = 0; a < width; a++)
			System.out.print("-");
		System.out.println("+");

		System.out.println(" Do you want to pick this character?");
		System.out.println(" 1. Yes");
		System.out.println(" 2. No");
		System.out.println("");
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
