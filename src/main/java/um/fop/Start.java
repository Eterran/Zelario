import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.lang.Thread.*;
import javax.swing.text.*;

import Entitypack.*;
import Entitypack.Monsterpack.Harpy;
import Entitypack.Playerpack.*;
import Gamepack.*;
import Gamepack.ConsoleToGUI.CustomOutputStream;
import Mappack.*;
import PickCharacter.*;

import SQLpack.*;
import java.io.*;
import java.sql.*;

public class Start {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Zelario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 780);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setBorder(null);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JTextField userInputField = new JTextField(20);
        userInputField.setBackground(Color.BLACK);
        userInputField.setForeground(Color.WHITE); 
        userInputField.setBorder(null); 
        userInputField.setFont(new Font("Monospaced", Font.PLAIN, 14)); 

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        // Adding components to the frame
        frame.getContentPane().add(scrollPane, "Center");
        
        // Redirecting System.out to the text area
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);

        // Panel for user input
        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(userInputField, BorderLayout.CENTER);
        ConsoleToGUI consoleToGUI = new ConsoleToGUI(userInputField);

        // Adding components to the frame
        frame.getContentPane().add(new JScrollPane(textArea), "Center");
        frame.getContentPane().add(inputPanel, "South");
        frame.setVisible(true);

        // String username = "null", pw = "null";
        // Connection conn = null;
        // try {
        //     conn = SQL.getConnection();
        // } catch (Exception e) {
        //     System.out.println("Unable to establish connection with the server.");
        //     return;
        // }
        // try {
        //     LoginMenu.startingScreen();
        // } catch (IOException e) {
        //     System.out.println("File not found. (Starting Screen)");
        // }

        // String input = "";
		// try {
		// 	do {
		// 		input = consoleToGUI.getNextInput();
		// 		if (!input.matches("^[1-2]$")) {
		// 			System.out.println("Invalid input.");
		// 		}
		// 	} while (!input.matches("^[1-2]$"));
        // } catch (InterruptedException e) {
            
        // }

        // switch (input){
        //     case "1":
        //         while(true){
        //             try {
        //                 System.out.println("Register");
        //                 System.out.println("Enter your username: ");
        //                 try {
        //                     username = consoleToGUI.getNextInput();
        //                 } catch (Exception e) {
        //                     e.printStackTrace();
        //                 }
        //                 System.out.println("Enter your password: ");
        //                 try {
        //                     pw = consoleToGUI.getNextInput();
        //                 } catch (Exception e) {
        //                     e.printStackTrace();
        //                 }
        //                 if (SQL.usernameExists(conn, username)) {
        //                     System.out.println("Username already exists. Please choose a different username.");
        //                     continue;
        //                 }
        //                 SQL.registerUser(conn, username, pw);
        //                 break;
        //             } catch (SQLException e) {
        //                 System.out.println("Error while registering user or connecting to the database.");
        //             }
        //         }
        //         break;
        //     case "2":
        //     while (true){
        //         System.out.println("Login");
        //         System.out.println("Enter your username: ");
        //         try {
        //             username = consoleToGUI.getNextInput();
        //         } catch (Exception e) {
        //             e.printStackTrace();
        //         }
        //         System.out.println("Enter your password: ");
        //         try {
        //             pw = consoleToGUI.getNextInput();
        //         } catch (Exception e) {
        //             e.printStackTrace();
        //         }
        //         try {
        //             if (!(SQL.usernameExists(conn, username))) {
        //                 System.out.println("Username does not exists, please try again.");
        //             }
        //         } catch (SQLException e) {
        //             System.out.println("Unable to establish connection with the server.");
        //             return;
        //         }
        //         String existingPassword;
        //         try {
        //             existingPassword = SQL.getPassword(conn, username);
        //         } catch (Exception e) {
        //             System.out.println("Unable to establish connection with the server.");
        //             return;
        //         }
        //         while(true){
        //             if (existingPassword == null) {
        //                 System.out.println("Username does not exist.");
        //                 return;
        //             } else if (!existingPassword.equals(pw)) {
        //                     System.out.println("Incorrect password.");
        //                     System.out.println("Enter your password: ");
        //                     try {
        //                         pw = consoleToGUI.getNextInput();
        //                     } catch (Exception e) {
        //                         e.printStackTrace();
        //                     }
        //             } else {
        //                 break;
        //             }
        //         }
        //         break;
        //     }
        // }
        // try {
        //     String saveFilePath = SQL.getSaveFile(conn, username);
        //     File saveFile = new File(saveFilePath);
        //     if (saveFile.exists()) {
        //         try(BufferedReader r = new BufferedReader(new FileReader(saveFile))){
        //             String line;
        //             while((line = r.readLine()) != null){
        //                 System.out.println(line);
        //             }
        //         } catch(IOException e){
        //             System.out.println("The file does not exists. (Somehow)");
        //         }
        //     }
        // } catch (SQLException e) {
        //     System.out.println("Unable to establish connection with the server.");
        // }

        while(PickCharacter.heroChoice == -1){
            try {
                PickCharacter.pickCharacterMenu(consoleToGUI, userInputField, textArea);
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }
        Entity player;
        if(PickCharacter.heroChoice == 1){
            player = new Rogue();
        } else if(PickCharacter.heroChoice == 2){
            player = new Warrior();
        } else if(PickCharacter.heroChoice == 3){
            player = new Archer();
        } else if(PickCharacter.heroChoice == 4){
            player = new Mage();
        } else {
            player = new Paladin();
        }
        Game game = new Game(player);
        while(true){
            game.beginCombat(player, new Harpy());
        }
    }
}