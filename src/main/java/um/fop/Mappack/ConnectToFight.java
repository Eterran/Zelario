package Mappack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import Mappack.GameWinForReal;

public class ConnectToFight extends JFrame { //override方法重写alt+enter

    public static void main(String[] args) {
        ConnectToFight frame = new ConnectToFight();
        frame.setVisible(true);
    }

    private final int WIDTH = 40;
    private final int HEIGHT = 40;
    private final char OBSTACLE = '#';
    private final char PLAYER = '@';
    private char[][] map;
    private int playerX, playerY;
    private char A;
    private char X;
    private char R;

    public ConnectToFight() {
        super("Zelario Game");

        JPanel panel = new JPanel(new GridLayout(HEIGHT, WIDTH));
        panel.setBackground(Color.BLACK);
        map = new char[HEIGHT][WIDTH];

        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                map[i][j] = '.'; // 假设'.'代表可移动空间
            }
        }

        // 地图的边界
        for (int i = 0; i < 40; i++) {
            map[i][0] = '#';
            map[i][39] = '#';
        }
        for (int j = 0; j < 40; j++) {
            map[0][j] = '#';
            map[39][j] = '#';
        }
//大山
        int height = 8; // 山脉的高度
        int mountainStart = 20; // 山脉开始的列位置
        int mountainRowStart = 1; // 山脉开始的行位置

        for (int i = mountainRowStart; i < mountainRowStart + height; i++) {
            // 左边斜面到右边斜面之间填充
            for (int j = mountainStart - (i - mountainRowStart); j < mountainStart + (i - mountainRowStart); j++) {
                map[i][j] = '*'; // 用字符填充山脉中间
            }
            // 设置左边斜面
            map[i][mountainStart - (i - mountainRowStart)] = '/';
            // 设置右边斜面
            map[i][mountainStart + (i - mountainRowStart)] = '\\';
        }

//左山
        int height2 = 6; // 山脉的高度
        int mountainStart2 = 6; // 山脉开始的列位置
        int mountainRowStart2 = 10; // 山脉开始的行位置

        for (int i = mountainRowStart2; i < mountainRowStart2 + height2; i++) {
            // 左边斜面到右边斜面之间填充
            for (int j = mountainStart2 - (i - mountainRowStart2); j < mountainStart2 + (i - mountainRowStart2); j++) {
                map[i][j] = '^'; // 用字符填充山脉中间
            }
            // 设置左边斜面
            map[i][mountainStart2 - (i - mountainRowStart2)] = '/';
            // 设置右边斜面
            map[i][mountainStart2 + (i - mountainRowStart2)] = '\\';
        }
//右山
        int height3 = 6; // 山脉的高度
        int mountainStart3 = 33; // 山脉开始的列位置
        int mountainRowStart3 = 10; // 山脉开始的行位置

        for (int i = mountainRowStart3; i < mountainRowStart3 + height3; i++) {
            // 左边斜面到右边斜面之间填充
            for (int j = mountainStart3 - (i - mountainRowStart3); j < mountainStart3 + (i - mountainRowStart3); j++) {
                map[i][j] = '^'; // 用字符填充山脉中间
            }
            // 设置左边斜面
            map[i][mountainStart3 - (i - mountainRowStart3)] = '/';
            // 设置右边斜面
            map[i][mountainStart3 + (i - mountainRowStart3)] = '\\';
        }

//河流
        int riverHeight = 16; // river的高度
        int riverStart = 14; // river开始的列位置
        int riverRowStart = 16; // river开始的行位置

        for (int i = riverRowStart; i < riverRowStart + riverHeight; i++) {
            map[i][riverStart] = '('; // 河流左边界
            for (int j = riverStart + 1; j < riverStart + 11; j++) {
                map[i][j] = '~'; // 河流中间部分
            }
            map[i][riverStart + 11] = ')'; // 河流右边界
        }

//两个角
        int Sh = 7; // 三角形的高度
        int Ss = 1; // 三角形开始的列位置
        int Sr = 32; // 三角形开始的行位置

        for (int i = 0; i < Sh; i++) {
            for (int j = 0; j <= i; j++) {
                map[Sr + i][Ss + j] = '!';
            }
        }

        int Sh2 = 7; // 三角形的高度
        int Ss2 = 38; // 三角形开始的列位置
        int Sr2 = 32; // 三角形开始的行位置

        for (int i = 0; i < Sh2; i++) {
            for (int j = 0; j <= i; j++) {
                map[Sr2 + i][Ss2 - j] = '!';
            }
        }

        map[38][18] = 'B';
        map[38][19] = 'O';
        map[38][20] = 'S';
        map[38][21] = 'S';

        playerX = 20;
        playerY = 14;
        map[playerY][playerX] = PLAYER;
        map[10][10] = 'A';

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                JLabel label = new JLabel(String.valueOf(map[i][j]));
                label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));//字体被设置为等宽字体（Font.MONOSPACED），风格是普通（Font.PLAIN），大小为12
                panel.add(label);
            }
        }
        // 添加键盘监听器
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int dx = 0, dy = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        dy = -1;
                        break;
                    case KeyEvent.VK_DOWN:
                        dy = 1;
                        break;
                    case KeyEvent.VK_LEFT:
                        dx = -1;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dx = 1;
                        break;
                    case KeyEvent.VK_ESCAPE:  // 监听ESC键 要不要无所谓把
                        // 是=0；否=1；
                        int choice = JOptionPane.showConfirmDialog(null, "是否重新开始游戏？", "确认", JOptionPane.YES_NO_OPTION);
                        if (choice == 0) {
                            // 游戏重新开始，重新生成地图和玩家位置
                            dispose();
                            new GameWinForReal().setVisible(true);
                        } else {
                            // 退出游戏
                            // System.exit(0);
                        }
                        break;
                }
                //主角更新位置
                System.out.println(playerX);
                System.out.println(playerY);
                int newX = playerX + dx, newY = playerY + dy;
                if (map[newY][newX] != OBSTACLE && map[newY][newX] != 'A' && map[newY][newX] != '!'&& map[newY][newX] != '('&& map[newY][newX] != ')'&& map[newY][newX] != '~'&& map[newY][newX] != '*'&& map[newY][newX] != '\\'&& map[newY][newX] != '/'&& map[newY][newX] != '^') {
                    System.out.println("x=" + playerX);
                    map[playerY][playerX] = '.';//把之前的位置清零，以及上色
                    map[newY][newX] = PLAYER;
                    playerX = newX;
                    playerY = newY;
                    refreshMap(panel);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 480);
        setLocationRelativeTo(null);//null 参数意味着窗口将相对于屏幕的中央定位
        setFocusable(true);//可以获取焦点
        requestFocus();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("successful！");//只在控制台显示
                refreshMap(panel);
                // 移动下player，方便查看到 要不要无所谓 copy来的 有点太高级了
                try {
                    Robot robot = new Robot();
                    robot.setAutoDelay(100); // 延迟1秒

                    // 模拟按下下箭头
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);

                } catch (AWTException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "you really wanna quit the game？", "you really wanna leave us?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

        });
    }

    // 刷新地图
    private void refreshMap(JPanel panel) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                JLabel label = (JLabel) panel.getComponent(i * WIDTH + j);
                char ch = map[i][j];
//
                if (ch == OBSTACLE) {
                    label.setForeground(Color.GRAY);
                    label.setText(String.valueOf(OBSTACLE));
                } else if (ch == 'A') {
                    label.setForeground(Color.YELLOW);
                    label.setText(String.valueOf('A'));
                } else if (ch == '*') {
                    label.setForeground(Color.RED);
                    label.setText(String.valueOf('*'));
                } else if (ch == '/') {
                    label.setForeground(Color.RED);
                    label.setText(String.valueOf('/'));
                } else if (ch == '(') {
                    label.setForeground(Color.RED);
                    label.setText(String.valueOf('('));
                } else if (ch == ')') {
                    label.setForeground(Color.RED);
                    label.setText(String.valueOf(')'));
                } else if (ch == '^') {
                    label.setForeground(Color.RED);
                    label.setText(String.valueOf('^'));
                } else if (ch == '!') {
                    label.setForeground(Color.RED);
                    label.setText(String.valueOf('!'));
                } else if (ch == '~') {
                    label.setForeground(Color.RED);
                    label.setText(String.valueOf('~'));
                } else if (ch == '\\') {
                    label.setForeground(Color.RED);
                    label.setText(String.valueOf('\\'));
                } else if (ch == PLAYER) {
                    System.out.println("1=" + playerX);
                    System.out.println("1=" + playerY);
                    label.setForeground(Color.pink);
                    label.setText(String.valueOf(PLAYER));
                } else {
                    label.setForeground(Color.GRAY);
                    label.setText(".");
                }
            }


        }
    }
}

