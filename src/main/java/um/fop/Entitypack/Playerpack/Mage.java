package Entitypack.Playerpack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Entitypack.Entity;
import Entitypack.Status;

public class Mage extends Player {
     public Mage() {
          super();
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Stat for player\\Mage.txt"));
               String temp;
               String[] stats;

               while(input.hasNextLine()){
                    temp = input.nextLine();
                    stats = temp.split(",");

                    this.name = stats[0];
                    this.HP = Integer.parseInt(stats[1]);
                    this.MP = Integer.parseInt(stats[2]);
                    this.maxHP = Integer.parseInt(stats[1]);
                    this.maxMP = Integer.parseInt(stats[2]);
                    this.physicalAttack = Integer.parseInt(stats[3]);
                    this.magicalAttack = Integer.parseInt(stats[4]);
                    this.physicalDefence = Integer.parseInt(stats[5]);
                    this.magicalDefence = Integer.parseInt(stats[6]);
               }


          }catch(FileNotFoundException e){
               System.out.println("File was not found");
          }
          
          this.skillOneName = "Poison";
          this.skill1Description = "Monster will be poisoned for 2 rounds.";
          this.skillTwoName = "Fireball";
          this.skill2Description = "Hurls a fiery projectile at the target, dealing moderate fire damage.";
          this.skillThreeName = "Frost";
          this.skill3Description = "The Mage releases a burst of frost, freezing nearby enemies in place for 2 rounds.";
          this.skill1 = 20; // Poison LV5 unlock
          this.skill2 = 100; // Fireball LV10 Unlock
          this.skill3 = 200; // Frost Nova LV30 Unlock & can freeze all opponent
          this.skill1Mp = 30; 
          this.skill2Mp = 40; 
          this.skill3Mp = 70;
          this.MaxCDSkill1 = 4;
          this.MaxCDSkill2 = 2;
          this.MaxCDSkill3 = 6;
          this.CDSkill1 = 4;
          this.CDSkill2 = 2;
          this.CDSkill3 = 6;
     }

     

     public int useSkill1(Entity target) {
          setCDSkill1(this.getMaxCDSkill1());
          this.setMP(this.getMP() - this.getSkill1Mp());
          int dmg = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          target.applyStatus(Status.POISONED, 2);
          return dmg;
         
     }

     public int useSkill2(Entity target) {
          setCDSkill2(this.getMaxCDSkill2());
          this.setMP(this.getMP() - this.getSkill2Mp());
          int dmg = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int useSkill3(Entity target) {
          setCDSkill3(this.getMaxCDSkill3());
          this.setMP(this.getMP() - this.getSkill3Mp());
          int dmg = (int) (this.getSkill3() * (1.0 - target.getMagicalDefence() / 100.0));
          this.damageDealt(target, dmg);
           target.applyStatus(Status.FROZEN, 2);
          return dmg;
     }

     public int normalAttack(Entity target) {// magical normal attack
          int dmg = (int) (this.magicalAttack * (1.0 - target.getMagicalDefence() / 100.0) * 0.2); // player HP
          this.damageDealt(target, dmg);
          return dmg;
     }
}
