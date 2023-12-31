package Entitypack.Playerpack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Entitypack.Entity;
import Entitypack.Status;

public class Rogue extends Player {
     public Rogue() {
          super();
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Stat for player\\Rogue.txt"));
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
         
          this.skillOneName = "Smite\t";
          this.skill1Description = "Slash a hit to monster.";
          this.skillTwoName = "Backstab\t";
          this.skill2Description = "The Rogue sneaks behind the target, delivering a devastating backstab. ";
          this.skillThreeName = "ShadowStep\t";
          this.skill1Description = "The Rogue can evade the next spell or attack from the enemies.";
          this.skill1 = 50; // Smite
          this.skill2 = 150; // Backstab (stun one round)
          this.skill3 = 0; // ShadowStep (immune spell or attack one time)
          this.skill1Mp = 30; 
          this.skill2Mp = 40; 
          this.skill3Mp = 50; 
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
          int dmg = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int useSkill2(Entity target) {
          setCDSkill2(this.getMaxCDSkill2());
          this.setMP(this.getMP() - this.getSkill2Mp());
          int dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          target.applyStatus(Status.STUNNED, 1);
          return dmg;
     }

     public int useSkill3(Entity target) {
          setCDSkill3(this.getMaxCDSkill3());
          this.setMP(this.getMP() - this.getSkill3Mp());
          this.applyStatus(Status.SHADOWSTEP, 9);
          return this.skill3;
     }

}
