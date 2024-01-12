package UIpack;
import javax.swing.*;
import javax.swing.text.*;

import java.io.OutputStream;
import java.awt.event.*;

public class ConsoleToGUI {
    public static class CustomOutputStream extends OutputStream {
        private JTextPane textPane;
        public CustomOutputStream(JTextPane textPane) {
            this.textPane = textPane;
        }
        @Override
        public void write(int b) {
            // Convert the byte to a string
            String text = String.valueOf((char) b);

            // Get the StyledDocument from the JTextPane
            StyledDocument doc = textPane.getStyledDocument();

            try {
                doc.insertString(doc.getLength(), text, null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        textPane.setCaretPosition(doc.getLength());
        }
    }
    
    private String userInput = null;
    private final Object lock = new Object();
    private JTextField userInputField;

    public ConsoleToGUI(JTextField userInputField) {
        this.userInputField = userInputField;
        setupInputField();
    }
    public void setupInputField() {
        userInputField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    synchronized (lock) {
                        userInput = userInputField.getText();
                        userInputField.setText("");
                        lock.notify();
                    }
                }
            }
        });
    }
    public String getNextInput() throws InterruptedException {
        while (userInput == null) {
            synchronized (lock) {
                lock.wait();
            }
        }
        String input = userInput;
        userInput = null;
        return input;
    }
}
