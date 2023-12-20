import java.util.Scanner;

import Entitypack.*;
import Entitypack.Playerpack.*;
import Gamepack.*;
import SQLpack.*;
import java.sql.*;

public class Start {
    public static void main(String[] args) {
        Entity player = new Paladin();
        Game game = new Game(player);
        String username, pw;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your username: ");
            username = s.nextLine();
        System.out.println("Enter your password: ");
            pw = s.nextLine();
        try {
            SQL.registerUser(SQL.getConnection(), username, pw);
        } catch (SQLException e) {
            System.out.println("Error while registering user or connecting to the database");
            e.printStackTrace();
        }
        while(true)
            game.beginCombat(player, game.spawnRandom());
    }
}