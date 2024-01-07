package Entitypack.Playerpack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Entitypack.Entity;
import Entitypack.Status;

public class Warrior extends Player {
     public Warrior() {
          super();
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Stat for player\\Warrior.txt"));
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
                    this.skillOneName = stats[7]+"\t";
                    this.skill1Description = stats[8];
                    this.skillTwoName = stats[9];
                    this.skill2Description = stats[10];
                    this.skillThreeName = stats[11]+"\t";
                    this.skill3Description = stats[12]+"\n\t\t\t"+stats[13];
                    this.skill1 = Integer.parseInt(stats[14]); 
                    this.skill2 = Integer.parseInt(stats[15]); 
                    this.skill3 = Integer.parseInt(stats[16]); 
                    this.skill1Mp = Integer.parseInt(stats[17]); 
                    this.skill2Mp = Integer.parseInt(stats[18]); 
                    this.skill3Mp = Integer.parseInt(stats[19]);
                    this.MaxCDSkill1 = Integer.parseInt(stats[20]);
                    this.MaxCDSkill2 = Integer.parseInt(stats[21]);
                    this.MaxCDSkill3 = Integer.parseInt(stats[22]);
                    this.CDSkill1 = Integer.parseInt(stats[20]);
                    this.CDSkill2 = Integer.parseInt(stats[21]);
                    this.CDSkill3 = Integer.parseInt(stats[22]);
               }


          }catch(FileNotFoundException e){
               System.out.println("File was not found");
          }
         
     }

     public int useSkill1(Entity target) {
          setCDSkill1(this.getMaxCDSkill1());
          this.setMP(this.getMP() - this.getSkill1Mp());
          int dmg = (int) ( this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
          return this.damageDealt(target, dmg);
     }

     public int useSkill2(Entity target) {
          setCDSkill2(this.getMaxCDSkill2());
          this.setMP(this.getMP() - this.getSkill2Mp());
          int dmg = (int) (this.getPhysicalAttack() * (this.getSkill2()/100) * (1.0 - target.getPhysicalDefence() / 100.0));
          return this.damageDealt(target, dmg);
     }

     public int useSkill3(Entity target) {
          setCDSkill3(this.getMaxCDSkill3());
          this.setMP(this.getMP() - this.getSkill3Mp());
          this.applyStatus(Status.WARRIORDMGRESIST, 3);
          return getSkill3();
      }
      
}
