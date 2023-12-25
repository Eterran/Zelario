import java.util.Scanner;
import Entitypack.*;
import Entitypack.Playerpack.*;
import Gamepack.*;
import SQLpack.*;
import java.io.*;
import java.sql.*;

public class Start {
    public static void main(String[] args) {
        String username = "null", pw = "null";
        Scanner s = new Scanner(System.in);
        Connection conn = null;
        try {
            conn = SQL.getConnection();
        } catch (Exception e) {
            System.out.println("Unable to establish connection with the server.");
            s.close();
            return;
        }
        System.out.println("1. Register\n2. Login");
        int c;
        while(true){
            try {
                c = Integer.parseInt(s.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
        switch (c){
            case 1:
                while(true){
                    try {
                        System.out.println("Register");
                        System.out.println("Enter your username: ");
                        username = s.nextLine();
                        System.out.println("Enter your password: ");
                        pw = s.nextLine();
                        if (SQL.usernameExists(conn, username)) {
                            System.out.println("Username already exists. Please choose a different username.");
                            continue;
                        }
                        SQL.registerUser(conn, username, pw);
                        break;
                    } catch (SQLException e) {
                        System.out.println("Error while registering user or connecting to the database.");
                    }
                }
                break;
            case 2:
            while (true){
                System.out.println("Login");
                System.out.println("Enter your username: ");
                username = s.nextLine();
                System.out.println("Enter your password: ");
                pw = s.nextLine();
                try {
                    if (!(SQL.usernameExists(conn, username))) {
                        System.out.println("Username does not exists, please try again.");
                    }
                } catch (SQLException e) {
                    System.out.println("Unable to establish connection with the server.");
                    s.close();
                    return;
                }
                String existingPassword;
                try {
                    existingPassword = SQL.getPassword(conn, username);
                } catch (Exception e) {
                    System.out.println("Unable to establish connection with the server.");
                    s.close();
                    return;
                }
                while(true){
                    if (existingPassword == null) {
                        System.out.println("Username does not exist.");
                        s.close();
                        return;
                    } else if (!existingPassword.equals(pw)) {
                            System.out.println("Incorrect password.");
                            System.out.println("Enter your password: ");
                            pw = s.nextLine();
                    } else {
                        break;
                    }
                }
                break;
            }
        }
        try {
            String saveFilePath = SQL.getSaveFile(conn, username);
            File saveFile = new File(saveFilePath);
            if (saveFile.exists()) {
                try(BufferedReader r = new BufferedReader(new FileReader(saveFile))){
                    String line;
                    while((line = r.readLine()) != null){
                        System.out.println(line);
                    }
                } catch(IOException e){
                    System.out.println("The file does not exists. (Somehow)");
                }
            }
        } catch (SQLException e) {
            System.out.println("Unable to establish connection with the server.");
        }
        Entity player = new Paladin();
        Game game = new Game(player);
        while(true){
            game.beginCombat(player, game.spawnRandom());
        }
        
    }
}