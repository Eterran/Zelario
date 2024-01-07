import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.awt.event.*;

import Entitypack.*;
import Entitypack.Playerpack.*;
import Gamepack.*;
import Mappack.*;
import PickCharacter.*;
import UIpack.*;
import UIpack.ConsoleToGUI.*;

import java.io.*;
import SQLpack.*;
import java.sql.*;

public class Start {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Zelario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(815, 810);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.getContentPane().setLayout(new BorderLayout());

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setBackground(Color.BLACK);
        textPane.setForeground(Color.WHITE);
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textPane.setBorder(new LineBorder(Color.BLACK));

        JTextField userInputField = new JTextField(20);
        userInputField.setText("Enter input here");
        userInputField.setForeground(Color.GRAY);

        JLabel promptLabel = new JLabel(">");
        promptLabel.setOpaque(true);
        promptLabel.setBackground(Color.BLACK);
        promptLabel.setForeground(Color.WHITE);
        promptLabel.setBorder(new LineBorder(Color.BLACK));
        promptLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));

        Timer timer = new Timer(500, e -> {
            if (promptLabel.getText().equals(">")) {
                promptLabel.setText(" ");
            } else {
                promptLabel.setText(">");
            }
        });

        userInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userInputField.getText().equals("Enter input here")) {
                    userInputField.setText("");
                    userInputField.setForeground(Color.WHITE);
                }
                timer.start();
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userInputField.getText().isEmpty()) {
                    userInputField.setText("Enter input here");
                    userInputField.setForeground(Color.GRAY);
                }
                timer.stop();
            }
        });

        userInputField.setBackground(Color.BLACK);
        userInputField.setBorder(null);
        userInputField.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        PrintStream printStream = new PrintStream(new CustomOutputStream(textPane));
        System.setOut(printStream);

        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(userInputField, BorderLayout.CENTER);
        inputPanel.add(promptLabel, BorderLayout.WEST);
        ConsoleToGUI consoleToGUI = new ConsoleToGUI(userInputField);

        // Adding components to the frame
        frame.getContentPane().add(new JScrollPane(textPane), "Center");
        frame.getContentPane().add(inputPanel, "South");
        frame.setVisible(true);
        
        Entity player = null;
        String username = "null", pw = "null";
        Connection conn = null;
        try {
            conn = SQL.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            LoginMenu.startingScreen();
        } catch (IOException e) {
            System.out.println("File not found. (Starting Screen)");
        }
        String input = "";
        try {
            do {
                input = consoleToGUI.getNextInput();
                if (!input.matches("^[1-2]$")) {
                }
            } while (!input.matches("^[1-2]$"));
        } catch (InterruptedException e) {

        }
        switch (input) {
            case "1":
                while (true) {
                    try {
                        System.out.println("Register");
                        System.out.print("+");

                        int width = 86;
                        for (int a = 0; a < width; a++)
                            System.out.print("-");
                        System.out.println("+");
                        System.out.print("Enter your username: ");
                        try {
                            username = consoleToGUI.getNextInput();
                            System.out.println(username);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.print("Enter your password: ");
                        try {
                            pw = consoleToGUI.getNextInput();
                            System.out.println(pw);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (SQL.usernameExists(conn, username)) {
                            System.out.println("Username already exists. Please choose a different username.");
                            continue;
                        }
                        SQL.registerUser(conn, username, pw);
                        SQL.setCurrentUsername(username);
                        break;
                    } catch (SQLException e) {
                        System.out.println("Error while registering user or connecting to the database.");
                        e.printStackTrace();
                    }
                }
                break;
            case "2":
                while (true) {
                    System.out.println("Login");
                    System.out.print("Enter your username: ");
                    try {
                        username = consoleToGUI.getNextInput();
                        System.out.println(username);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.print("Enter your password: ");
                    try {
                        pw = consoleToGUI.getNextInput();
                        System.out.println(pw);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.print("+");

                    int width = 86;
                    for (int a = 0; a < width; a++)
                        System.out.print("-");
                    System.out.println("+");
                    try {
                        if (!(SQL.usernameExists(conn, username))) {
                            System.out.println("Username does not exists, please try again.");
                        }
                    } catch (SQLException e) {
                        System.out.println("Unable to establish connection with the server.");
                        return;
                    }
                    String existingPassword;
                    try {
                        existingPassword = SQL.getPassword(conn, username);
                    } catch (Exception e) {
                        System.out.println("Unable to establish connection with the server.");
                        return;
                    }
                    while (true) {
                        if (!existingPassword.equals(pw)) {
                            System.out.println("Incorrect password.");
                            System.out.println("Enter your password: ");
                            try {
                                pw = consoleToGUI.getNextInput();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            SQL.setCurrentUsername(username);
                            break;
                        }
                    }
                    break;
                }
            try {
                String saveFilePath = SQL.getSaveFile(conn, username);
                File saveFile = new File(saveFilePath);
                if (saveFile.exists()) {
                    GameProgress progress = LoadGame.loadGameProgress(saveFilePath);
                    player = progress.getPlayerEntity();
                }
            } catch (SQLException e) {
                System.out.println("Unable to establish connection with the server.");
            }
        }
        if(player == null){
            while (PickCharacter.heroChoice == -1) {
                try {
                    PickCharacter.pickCharacterMenu(consoleToGUI, userInputField, textPane);
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                }
            }
            if (PickCharacter.heroChoice == 1) {
                player = new Rogue();
            } else if (PickCharacter.heroChoice == 2) {
                player = new Warrior();
            } else if (PickCharacter.heroChoice == 3) {
                player = new Archer();
            } else if (PickCharacter.heroChoice == 4) {
                player = new Mage();
            } else {
                player = new Paladin();
            }
            try {
                SaveGame.saveGameProgress(SQL.getSaveFile(conn, username), new GameProgress(player));
            } catch (SQLException e) {
                System.out.println("Unable to establish connection with the server.");
                e.printStackTrace();
            }
        }
        player.setEXP(2000);
        player.levelUp();
        Game game = new Game(player);
        textPane.setText("");
        displayIntro(consoleToGUI, scrollPane, textPane);
        RandomMonsterMap.setFrame(new RandomMonsterMap(player, Game.spawnRandom(player), textPane, consoleToGUI, frame,
                game, scrollPane, conn));
        RandomMonsterMap.getMapFrame().setVisible(true);
        frame.setVisible(false);
    }

    public static void displayIntro(ConsoleToGUI consoleToGUI, JScrollPane scrollPane, JTextPane textPane) {
        System.out.println("\r\n" + //
                "In the age of Zelario, a realm steeped in magic and mystery, a prophecy unfolds. Legends speak of an ancient artifact, the Heartstone, hidden deep within the Forbidden Forest. This mystical gem is said to hold unimaginable power, capable of shaping the destiny of the entire realm.\r\n"
                + //
                "\r\n" + //
                "You, a humble adventurer seeking fame and fortune, have heard whispers of the Heartstone's existence. Driven by a desire for glory, you embark on a perilous journey to uncover the truth behind the legends. As you delve into the Forbidden Forest, you soon realize that dark forces also seek the Heartstone for nefarious purposes.\r\n"
                + //
                "\r\n" + //
                "Guided by the words of a wise old sage, you must slay foes and gather EXP to be strong enough to reach the Heartstone's secret location.\r\n"
                + //
                "\r\n" + //
                "Your journey is a race against time, as the shadows of an ancient evil gather strength. The fate of Zelario rests on your shoulders. Will you emerge as the hero foretold by prophecy, or will darkness prevail? The adventure awaits, brave soul, as you step into the mystical world of Zelario and embark on a quest that will shape the destiny of an entire realm.");
        try {
            StyledDocument doc = textPane.getStyledDocument();
            doc.insertString(doc.getLength(), "\nPress Enter to continue.\n", ColorAttributes.DARK_GRAY);
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
}