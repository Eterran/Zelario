package Gamepack;
import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.event.*;

public class ConsoleToGUI {
    public static class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }
        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
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
        System.out.println("User input: " + input);
        return input;
    }
}
