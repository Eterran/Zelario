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
                    this.skillOneName = stats[7];
                    this.skill1Description = stats[8];
                    this.skillTwoName = stats[9];
                    this.skill2Description = stats[10];
                    this.skillThreeName = stats[11];
                    this.skill3Description = stats[12];
                    this.skill1 = Integer.parseInt(stats[13]); // Poison LV5 unlock
                    this.skill2 = Integer.parseInt(stats[14]); // Fireball LV10 Unlock
                    this.skill3 = Integer.parseInt(stats[15]); // Frost Nova LV30 Unlock & can freeze all opponent
                    this.skill1Mp = Integer.parseInt(stats[16]); 
                    this.skill2Mp = Integer.parseInt(stats[17]); 
                    this.skill3Mp = Integer.parseInt(stats[18]);
                    this.MaxCDSkill1 = Integer.parseInt(stats[19]);
                    this.MaxCDSkill2 = Integer.parseInt(stats[20]);
                    this.MaxCDSkill3 = Integer.parseInt(stats[21]);
                    this.CDSkill1 = Integer.parseInt(stats[19]);
                    this.CDSkill2 = Integer.parseInt(stats[20]);
                    this.CDSkill3 = Integer.parseInt(stats[21]);   
               }


          }catch(FileNotFoundException e){
               System.out.println("File was not found");
          }
          
         
     }

     

     public int useSkill1(Entity target) {
          setCDSkill1(this.getMaxCDSkill1());
          this.setMP(this.getMP() - this.getSkill1Mp());
          int dmg = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
          target.applyStatus(Status.POISONED, 2);
          return this.damageDealt(target, dmg);
         
     }

     public int useSkill2(Entity target) {
          setCDSkill2(this.getMaxCDSkill2());
          this.setMP(this.getMP() - this.getSkill2Mp());
          int dmg = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0));
          return this.damageDealt(target, dmg);
     }

     public int useSkill3(Entity target) {
          setCDSkill3(this.getMaxCDSkill3());
          this.setMP(this.getMP() - this.getSkill3Mp());
          int dmg = (int) (this.getSkill3() * (1.0 - target.getMagicalDefence() / 100.0));
           target.applyStatus(Status.FROZEN, 2);
           return this.damageDealt(target, dmg);
     }

     public int normalAttack(Entity target) {// magical normal attack
          int dmg = (int) (this.magicalAttack * (1.0 - target.getMagicalDefence() / 100.0) * 0.2); // player HP
          return this.damageDealt(target, dmg);
     }
}
