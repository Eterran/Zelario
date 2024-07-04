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
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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

        PrintStream printStream = new PrintStream(new CustomOutputStream(textPane));
        System.setOut(printStream);

        JPanel inputPanel = new JPanel();

        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(userInputField, BorderLayout.CENTER);
        inputPanel.add(promptLabel, BorderLayout.WEST);
        ConsoleToGUI consoleToGUI = new ConsoleToGUI(userInputField);

        frame.getContentPane().add(new JScrollPane(textPane), "Center");
        frame.getContentPane().add(inputPanel, "South");
        frame.setVisible(true);
        displayLogo(consoleToGUI, textPane, frame);
        textPane.setText("");
        
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
                if (!input.matches("^[1-3]$")) {
                    System.out.println("Invalid input. Please input 1-3 only.");
                }
            } while (!input.matches("^[1-3]$"));
        } catch (InterruptedException e) {
            e.printStackTrace();
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
                            } else {
                                break;
                            }
                        } catch (SQLException e) {
                            System.out.println("Unable to establish connection with the server.");
                            return;
                        }
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
                String saveFilePath = "saves\\" + username + ".dat";
                File saveFile = new File(saveFilePath);
                if (saveFile.exists()) {
                    GameProgress progress = LoadGame.loadGameProgress(saveFilePath);
                    player = progress.getPlayerEntity();
                }
            break;
            case "3":
                System.out.println("Exiting game...");
                System.exit(0);
            break;
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
        Game game = new Game(player);
        textPane.setText("");
        
        
        displayHeartStone(consoleToGUI, textPane, frame);
        displayIntro(consoleToGUI, textPane, frame);
        frame.setVisible(false);
        RandomMonsterMap.setFrame(new RandomMonsterMap(player, Game.spawnRandom(player), textPane, consoleToGUI, frame,
                game, conn));
        RandomMonsterMap.getMapFrame().setVisible(true);
    }

    public static void displayIntro(ConsoleToGUI consoleToGUI, JTextPane textPane, JFrame frame) {
        textPane.setFont(new Font("Garamond", Font.PLAIN, 19));
        try {
            textPane.setText("");
            File file = new File("src\\main\\java\\um\\fop\\ASCII\\Intro.txt");
            Scanner s;
            s = new Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                System.out.print("      ");
                for (char c : line.toCharArray()) {
                    System.out.print(c);
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println();
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            StyledDocument doc = textPane.getStyledDocument();
            System.out.println("\n\n");
            doc.insertString(doc.getLength(), " Press Enter to continue...\n", ColorAttributes.DARK_GRAY);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        String choice = "";
        try {
            choice = consoleToGUI.getNextInput();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14));
    }

    public static void displayHeartStone(ConsoleToGUI consoleToGUI, JTextPane textPane, JFrame frame){
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 6));
        textPane.setText("");
        try {
            List<String> fileNames = Arrays.asList(
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone1.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone2.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone3.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone2.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone1.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone4.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone5.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone6.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone7.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone8.txt",
                "src\\main\\java\\um\\fop\\ASCII\\HeartStone\\Heartstone9.txt"
            );
            List<String> frames = new ArrayList<>();
            for (String fileName : fileNames) {
                File file = new File(fileName);
                Scanner s = new Scanner(file);
                StringBuilder sb = new StringBuilder();
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    sb.append("        ");
                    sb.append(line);
                    sb.append("\n");
                }
                s.close();
                frames.add(sb.toString());
            }
            CountDownLatch latch = new CountDownLatch(1);

            Timer timer = new Timer(300, null);
            timer.addActionListener(new ActionListener() {
                int frameIndex = 0;
                public void actionPerformed(ActionEvent e) {
                    if (frameIndex < frames.size()) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                textPane.setText(frames.get(frameIndex));
                            }
                        });
                        frameIndex++;
                    } else {
                        ((Timer)e.getSource()).stop();
                        latch.countDown();
                    }
                }
            });
            timer.start();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void displayLogo(ConsoleToGUI consoleToGUI, JTextPane textPane, JFrame frame){
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 6));
        textPane.setText("");
        try {
            List<String> fileNames = Arrays.asList(
                "src\\main\\java\\um\\fop\\ASCII\\ZelarioLogo\\ZelarioLogo1.txt",
                "src\\main\\java\\um\\fop\\ASCII\\ZelarioLogo\\ZelarioLogo1.txt",
                "src\\main\\java\\um\\fop\\ASCII\\ZelarioLogo\\ZelarioLogo2.txt",
                "src\\main\\java\\um\\fop\\ASCII\\ZelarioLogo\\ZelarioLogo3.txt",
                "src\\main\\java\\um\\fop\\ASCII\\ZelarioLogo\\ZelarioLogo4.txt",
                "src\\main\\java\\um\\fop\\ASCII\\ZelarioLogo\\ZelarioLogo5.txt"
            );
            List<String> frames = new ArrayList<>();
            for (String fileName : fileNames) {
                File file = new File(fileName);
                Scanner s = new Scanner(file);
                StringBuilder sb = new StringBuilder();
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    sb.append("          ");
                    sb.append(line);
                    sb.append("\n");
                }
                s.close();
                frames.add(sb.toString());
            }
            CountDownLatch latch = new CountDownLatch(1);

            Timer timer = new Timer(300, null);
            timer.addActionListener(new ActionListener() {
                int frameIndex = 0;
                public void actionPerformed(ActionEvent e) {
                    if (frameIndex < frames.size() -1 ) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                textPane.setText(frames.get(frameIndex));
                            }
                        });
                        frameIndex++;
                    } else {
                        ((Timer)e.getSource()).stop();
                        latch.countDown();
                    }
                }
            });
            timer.start();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 14));
    }
}