package Mappack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
    public MainFrame() {
        super("Zelario Game");

        JButton btnStart = new JButton("Start Game");//按下后会打开一个新窗口//JButton是一个写了"Start Game"的按钮
        btnStart.addActionListener(new ActionListener() { //给按钮添加了动作监听器，就是说当按钮被点击时，将执行监听器中的actionPerformed的方法
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWinForReal gameFrame = new GameWinForReal();//调用GameWinForReal
                gameFrame.setVisible(true);//设置窗口可见

                dispose();//有dispose就代表第一个窗口自动关闭，不要dispose就会两个窗口同时存在
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());//GridBagLayout是一个灵活的布局管理器，它允许组件跨多行多列，并且可以调整大小和位置。
        GridBagConstraints c = new GridBagConstraints();//GridBagConstraints的用法是设置一个组件在网格布局中的布局参数
        c.gridx = 0;
        c.gridy = 0;//把这个组件放在网格的第一行第一列
        c.insets = new Insets(10, 10, 10, 10);//Insets是用来指定组件外围的填充，就是这个组件边缘与其所在网格单元边缘之间的空间（距离）
        panel.add(btnStart, c);//将 btnStart 的按钮添加到 panel里，这个按钮将出现在网格的左上角（因为c设置了00），然后周围距离边缘的像素为10

        JLabel lblInfo = new JLabel("Press ESC to restart the game");
        c.gridy = 1;
        panel.add(lblInfo, c);

        setContentPane(panel); //将整个 panel（含有btnStart和lblInfo）设置为窗口的内容面板，就是info和按钮都会显示在面板上
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭，程序就关闭
        setSize(400, 300); // 调整窗口大小
        setLocationRelativeTo(null);//将窗口置于屏幕中央
    }

}
