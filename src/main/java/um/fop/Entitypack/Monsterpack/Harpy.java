package Entitypack.Monsterpack;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Entitypack.*;

public class Harpy extends Monster {
    public Harpy(Entity player) {
        super();
        if(player.getLevel() >= 1 && player.getLevel() < 15){
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Default Monster stats\\Harpy.txt"));
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
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Level 20 Monster stats\\Harpy2.txt"));
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

      if(player.getLevel() >= 25 ){
          try{
               Scanner input = new Scanner(new FileInputStream("src\\main\\java\\um\\fop\\Stats\\Level 30 Monster stats\\Harpy3.txt"));
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
        this.skillOneName = "Talon Strike";
        this.skill1Description = "a physical damage strike.";
        this.skillTwoName = "Wind Gust";
        this.skill2Description = "a magical attack that can disrupt opponents.";
        this.skill1 = 180; // Talon strike
        this.skill2 = 180; // Wind Gust
        this.skill1Mp = 30; 
        this.skill2Mp = 30; 
        this.MaxCDSkill1 = 4;
        this.MaxCDSkill2 = 6;
        this.CDSkill1 = 2;
        this.CDSkill2 = 3;
        this.expDrop = 75;
    }

    public int useSkill1(Entity target) {
        setCDSkill1(this.getMaxCDSkill1());
        this.setMP(this.getMP() - this.getSkill1Mp());
        int dmg = (int) (this.getPhysicalAttack() * (this.getSkill1() / 100) * (3.5 - target.getPhysicalDefence() / 100.0)); // player HP
        return this.damageDealt(target, dmg);
    }

    public int useSkill2(Entity target) {
        setCDSkill2(this.getMaxCDSkill2());
        this.setMP(this.getMP() - this.getSkill2Mp());
        int dmg = (int) (this.getMagicalAttack() * (this.getSkill2() / 100) * (3.1 - target.getMagicalDefence() / 100.0)); // player HP, and add
        target.applyStatus(Status.WEAKENED, 2);
        return this.damageDealt(target, dmg);
    }

   public int normalAttack(Entity target) {//magical normal attack
        int dmg = (int) (this.getMagicalAttack() * (3.1 - target.getMagicalDefence() / 100.0)); // player HP
        return this.damageDealt(target, dmg);
    }
}
