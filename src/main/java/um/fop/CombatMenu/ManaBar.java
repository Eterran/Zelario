package CombatMenu;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import Entitypack.Entity;
import UIpack.ColorAttributes;

public class ManaBar {
    public void HeroManaBar(Entity player,JTextPane textPane ) 
    {
    	
    	int playerMana=player.getMP();
        int playerMaxMana=player.getMaxMP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) playerMana / playerMaxMana));

       try {
        StyledDocument doc = textPane.getStyledDocument();
        doc.insertString(doc.getLength(), "Mana   : ", ColorAttributes.BLUE);
        doc.insertString(doc.getLength(), String.format("%3d/", playerMana), ColorAttributes.BLUE);
        doc.insertString(doc.getLength(), String.format("%3d [", playerMaxMana), ColorAttributes.BLUE);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "|", ColorAttributes.BLUE);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } 
            else {
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), ".", ColorAttributes.BLUE);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }            
            }
        }
        try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "]", ColorAttributes.BLUE);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
    }
    
    public void MonsterManaBar(Entity monster,JTextPane textPane) {
    	
    	int monsterMana=monster.getMP();
    		
        int monsterMaxMana=monster.getMaxMP(); 
        int barLength = 30; // Adjust the length of the bar as needed
        int filledBar = (int) (barLength * ((double) monsterMana / monsterMaxMana));

         try {
        StyledDocument doc = textPane.getStyledDocument();
        doc.insertString(doc.getLength(), "Mana   : ", ColorAttributes.BLUE);
        doc.insertString(doc.getLength(), String.format("%3d/", monsterMana), ColorAttributes.BLUE);
        doc.insertString(doc.getLength(), String.format("%3d [", monsterMaxMana), ColorAttributes.BLUE);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < barLength; i++) {
            if (i < filledBar) {
                
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "|", ColorAttributes.BLUE);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } 
            else {
                try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), ".", ColorAttributes.BLUE);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }            
            }
        }
        try {
                    StyledDocument doc = textPane.getStyledDocument();
                    doc.insertString(doc.getLength(), "]", ColorAttributes.BLUE);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
    }
}

    