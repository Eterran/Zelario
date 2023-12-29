package CombatMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Entitypack.Entity;
import UIpack.ColorAttributes;
public class HealthBar2 {
    public void HeroHealthBar(Entity player,JTextPane textPane) 
    {
    	
    	int playerHealth= player.getHP();
        int playerMaxHealth= player.getMaxHP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) playerHealth / playerMaxHealth));

        try {
        StyledDocument doc = textPane.getStyledDocument();
        doc.insertString(doc.getLength(), "Health : ", ColorAttributes.RED);
        doc.insertString(doc.getLength(), String.format("%3d/", playerHealth), ColorAttributes.RED);
        doc.insertString(doc.getLength(), String.format("%3d [", playerMaxHealth), ColorAttributes.RED);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "|", ColorAttributes.RED);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } 
            else {
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), ".", ColorAttributes.RED);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }            
            }
        }
        try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "]", ColorAttributes.RED);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
               
    }
    
    public void MonsterHealthBar(Entity monster,JTextPane textPane) {
    	
    	int monsterHealth=monster.getHP();
        int monsterMaxHealth=monster.getMaxHP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) monsterHealth / monsterMaxHealth));

       try {
        StyledDocument doc = textPane.getStyledDocument();
        doc.insertString(doc.getLength(), "Health : ", ColorAttributes.RED);
        doc.insertString(doc.getLength(), String.format("%3d/", monsterHealth), ColorAttributes.RED);
        doc.insertString(doc.getLength(), String.format("%3d [", monsterMaxHealth), ColorAttributes.RED);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "|", ColorAttributes.RED);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } 
            else {
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), ".", ColorAttributes.RED);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }            
            }
        }
        try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "]", ColorAttributes.RED);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
    }
}

    