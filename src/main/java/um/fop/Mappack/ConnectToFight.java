package Mappack;


import Entitypack.Entity;
import Entitypack.Playerpack.Rogue;
import Gamepack.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConnectToFight extends JFrame { //override方法重写alt+enter

    public static void main(String[] args) {
        ConnectToFight frame = new ConnectToFight();
        frame.setVisible(true);
    }

    private final int WIDTH = 40;
    private final int HEIGHT = 40;
    private Game game;
    private Entity player;

    private char[][] map;

    public ConnectToFight() {
        super("Zelario Game");

        JPanel panel = new JPanel(new GridLayout(HEIGHT, WIDTH));
        panel.setBackground(Color.BLACK);
        map = new char[HEIGHT][WIDTH];
        this.player = new Rogue();
        this.game = new Game(player);

        JButton fightButton = new JButton("Fight");
        fightButton.addActionListener(e -> this.game.beginCombat(this.player, this.game.spawnRandom()));
        panel.add(fightButton);


        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                JLabel label = new JLabel(String.valueOf(map[i][j]));
                label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));//字体被设置为等宽字体（Font.MONOSPACED），风格是普通（Font.PLAIN），大小为12
                panel.add(label);
            }
        }

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 480);
        setLocationRelativeTo(null);//null 参数意味着窗口将相对于屏幕的中央定位
        setFocusable(true);//可以获取焦点
        requestFocus();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "you really wanna quit the game？", "you really wanna leave us?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

        });
    }

}

