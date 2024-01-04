package UIpack;
import javax.swing.*;
import javax.swing.text.*;

import java.io.OutputStream;
import java.awt.event.*;

public class ConsoleToGUI {
    public static class CustomOutputStream extends OutputStream {
        private JTextPane textPane;
        private StringBuilder sb = new StringBuilder();
        public CustomOutputStream(JTextPane textPane) {
            this.textPane = textPane;
        }
        @Override
        public void write(int b) {
            // Convert the byte to a string
            String text = String.valueOf((char) b);

            // Get the StyledDocument from the JTextPane
            StyledDocument doc = textPane.getStyledDocument();

            // Append the text to the StyledDocument
            try {
                doc.insertString(doc.getLength(), text, null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        // Scroll the JTextPane to the end of the text
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
    public void scrollToBottom(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar scrollBar = (JScrollBar) e.getAdjustable();
                scrollBar.setValue(scrollBar.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }    
    public void clearLastLines(JTextPane textPane, int numLines) {
        Document doc = textPane.getDocument();
        Element root = doc.getDefaultRootElement();
        int lineCount = root.getElementCount();
        int startLine = lineCount - numLines;
        if (startLine < 0) startLine = 0;
    
        try {
            int startOffset = root.getElement(startLine).getStartOffset();
            int endOffset = doc.getLength();
            doc.remove(startOffset, endOffset - startOffset);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
