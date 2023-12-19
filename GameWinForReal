package Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class GameWinForReal extends JFrame {
    private final int WIDTH = 40;
    private final int HEIGHT = 40;

    private final char BORDER = 'X';
    private final char OBSTACLE = '#';
    private final char PLAYER = '@';
    private final char BLOCKED = '.';

    //private final char GHOST = 'A';

    private char[][] map;
    private int playerX, playerY;

    public GameWinForReal() {
        super("Zelario Game");

        JPanel panel = new JPanel(new GridLayout(HEIGHT, WIDTH));
        panel.setBackground(Color.BLACK);
        map = new char[HEIGHT][WIDTH];

        // 随机绘制地图
        Random random = new Random();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == 0 || i == HEIGHT - 1 || j == 0 || j == WIDTH - 1) {
                    map[i][j] = BORDER;
                } else if (random.nextInt(10) < 2) {
                    map[i][j] = OBSTACLE;
                } else {
                    map[i][j] = ' ';
                }
            }
        }

        // 四边形障碍物设置
        for (int k = 0; k < 5; k++) { //搞了4个框框作为障碍物
            int x = random.nextInt(WIDTH - 6) + 3;//不让障碍物与边框重合
            int y = random.nextInt(HEIGHT - 6) + 3;
            int size = random.nextInt(3) + 2;//nextInt(3) 生成0-2的随机数

            System.out.println("x=" + x + "--y=" + y + "--size=" + size);

            for (int i = y - size - 1; i <= y + size + 1; i++) {
                for (int j = x - size - 1; j <= x + size + 1; j++) {
                    if (i == y - size - 1 || i == y + size + 1 || j == x - size - 1 || j == x + size + 1) {//检查当前的坐标 (i, j) 是否在正方形边框的边缘
                        if (i < 0) {
                            i = 0;
                        }
                        if (i >= HEIGHT) {
                            continue;
                        }
                        if (j < 0) {
                            j = 0;
                        }
                        if (j >= WIDTH) {
                            continue;
                        }
                        System.out.println("i=" + i + "--j=" + j);
                        if (map[i][j] == BLOCKED) {
                            //	如果已经是'.'，不再设置为BORDER
                        } else if (map[i][j] == BORDER && j != x - size - 1 && j != x + size + 1 && i != y - size - 1 && i != y + size + 1) {
                            //	如果已经是BORDER，还要设置为BORDER，说明是交接处，设置为'.'，但不能是最边上，最边上还是有设置为BORDER
                            map[i][j] = BLOCKED;
                        } else {
                            map[i][j] = BORDER;
                        }
                    } else {
                        if (i < 0) {
                            i = 0;
                        }
                        if (i >= HEIGHT) {
                            continue;
                        }
                        if (j < 0) {
                            j = 0;
                        }
                        if (j >= WIDTH) {
                            continue;
                        }
                        System.out.println("i=" + i + "--j=" + j);
                        map[i][j] = BLOCKED;
                    }
                }
            }
        }

        // 放置@字符
        do {
            playerX = random.nextInt(WIDTH - 2) + 1;
            playerY = random.nextInt(HEIGHT - 2) + 1;
        } while (map[playerY][playerX] != ' ');
        map[playerY][playerX] = PLAYER;


        // 绘制地图
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
                int newX = playerX + dx, newY = playerY + dy;
                if (map[newY][newX] != BORDER && map[newY][newX] != OBSTACLE) {
                    map[playerY][playerX] = ' ';//把之前的位置清零，以及上色
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
        requestFocus();//请求将焦点赋予给当前组件。因为：窗口一旦显示，就能立即响应用户的输入，like：键盘事件
        /*在图形用户界面（GUI）编程中，"焦点"指的是当前接收键盘输入和类似事件的组件。当一个组件有焦点时，它可以接收来自用户的键盘事件，
        比如按键。在一个窗口中，通常只有一个焦点所有者，这意味着在同一时间内，只有一个GUI组件（比如文本框、按钮等）可以接收键盘输入。
         */
        /*在实际应用中，焦点控制对于确保用户体验顺畅非常重要。例如，在一个表单中，当用户完成一个文本框的输入后，
        按下 "Tab" 键会将焦点移到下一个文本框，这样用户就可以继续输入，而不需要用鼠标点击每一个文本框。
        如果一个窗口或对话框弹出，通常开发者也会希望它立即获得焦点，这样用户就可以直接使用键盘进行操作，而不必先点击窗口。
         */

        // 窗口打开和关闭监听器
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
                if (ch == BLOCKED) {
                    label.setForeground(Color.GRAY);
                    label.setText(".");
                } else if (ch == BORDER) {
                    label.setForeground(Color.WHITE);
                    label.setText(String.valueOf(BORDER));
                } else if (ch == OBSTACLE) {
                    label.setForeground(Color.GRAY);
                    label.setText(String.valueOf(OBSTACLE));
                } else if (ch == PLAYER) {
                    label.setForeground(Color.YELLOW);
                    label.setText(String.valueOf(PLAYER));
                } else {
                    label.setForeground(Color.BLACK);
                    label.setText(" ");
                }
            }
        }
    }
}
