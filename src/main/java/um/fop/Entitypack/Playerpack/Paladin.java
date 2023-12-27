package Entitypack.Playerpack;

import Entitypack.*;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Paladin extends Player {
     public Paladin() {
          super();
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Paladin.txt"));
               String temp;
               String[] stats;

               while(input.hasNextLine()){
                    temp = input.nextLine();
                    stats = temp.split(",");

                    this.name = stats[0];
                    this.HP = Integer.parseInt(stats[1]);
                    this.MP = Integer.parseInt(stats[2]);
                    this.physicalAttack = Integer.parseInt(stats[3]);
                    this.magicalAttack = Integer.parseInt(stats[4]);
                    this.physicalDefence = Integer.parseInt(stats[5]);
                    this.magicalDefence = Integer.parseInt(stats[6]);
               }


          }catch(FileNotFoundException e){
               System.out.println("File was not found");
          }
          this.skillOneName = "Rage";
          this.skill1Description = "Paladin attack increased for 3 rounds.";
          this.skillTwoName = "Holy Smite";
          this.skill2Description = "Smashes the target with divine light, dealing damage and healing the Paladin for a portion of the damage dealt.";
          this.skillThreeName = "Divine Shield";
          this.skill3Description ="Creates a protective barrier around the Paladin, rendering them immune to damage for 2 rounds.";
          this.skill1 = 20; // Buff Physical Attack
          this.skill2 = 100; // Holy Smite, damage and heal a small amount
          this.skill3 = 0; // Divine Shield (immune damage 2 round)
          this.skill1Mp = 30; 
          this.skill2Mp = 40; 
          this.skill3Mp = 50;
          this.MaxCDSkill1 = 4;
          this.MaxCDSkill2 = 3;
          this.MaxCDSkill3 = 6;
          this.CDSkill1 = 4;
          this.CDSkill2 = 3;
          this.CDSkill3 = 6;
     }
     public int useSkill1(Entity target) {
          this.setMP(this.getMP() - this.getSkill1Mp());
          this.applyStatus(Status.PALADINSKILL1BUFF, 3);
          return getSkill1();
     }
     public int useSkill2(Entity target) {
          this.setMP(this.getMP() - this.getSkill2Mp());
          int dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.HP += (int) (damageDealt(target, dmg) * (30 / 100.0));

          if(this.HP > getMaxHP()){
               return getMaxHP();
          }
          
          this.damageDealt(target, dmg);
          return dmg;
     }
     public int useSkill3(Entity target) {
          this.setMP(this.getMP() - this.getSkill3Mp());
          this.applyStatus(Status.IMMUNITY, 2);
          return this.skill3;
     }
}
