package Entitypack.Monsterpack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Entitypack.Entity;

public class Witch extends Monster {
     public Witch(Entity player) {
          super();
          if(player.getLevel() >= 1 && player.getLevel() < 15){
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Default Monster stats\\Witch.txt"));
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

      }

      if(player.getLevel() >= 15 && player.getLevel() < 25){
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Level 20 Monster stats\\Witch2.txt"));
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

      }

      if(player.getLevel() >= 25){
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Level 30 Monster stats\\Witch3.txt"));
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

      }
          this.skillOneName = "Fireball";
          this.skill1Description = "a potent fire-based magical attack.";
          this.skill1 = 150; // Fireball
          this.skill1Mp = 20;
          this.MaxCDSkill1 = 4;
          this.CDSkill1 = 2;
          this.expDrop = 40;
     }

     public int useSkill1(Entity target) {
          setCDSkill1(this.getMaxCDSkill1());
          this.setMP(this.getMP() - this.getSkill1Mp());
          int dmg = (int) ( this.getMagicalAttack() * (this.getSkill1() / 100) * (3.1 - target.getMagicalDefence() / 100.0));
          return this.damageDealt(target, dmg);
     }

     public int normalAttack(Entity target) {// magical normal attack
          int dmg = (int) (this.getMagicalAttack() * (3.1 - target.getMagicalDefence() / 100.0) ); // player HP
          return this.damageDealt(target, dmg);
     }
}
