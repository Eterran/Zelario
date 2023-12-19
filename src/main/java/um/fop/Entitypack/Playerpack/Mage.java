package Entitypack.Playerpack;

import Entitypack.Entity;

public class Mage extends Archetypes {
     public Mage() {
          this.name = "Mage";
          this.HP = 200;
          this.MP = 150;
          this.physicalAttack = 30;
          this.magicalAttack = 100;
          this.physicalDefence = 20;
          this.magicalDefence = 30;
          this.skillOneName = "Poison";
          this.skillTwoName = "Fireball";
          this.skillThreeName = "Frost";
          this.skill1 = 20; // Poison LV5 unlock
          this.skill2 = 100; // Fireball LV10 Unlock
          this.skill3 = 200; // Frost Nova LV30 Unlock & can freeze all opponent
          this.CDSkill1 = 4;
          this.CDSkill2 = 2;
          this.CDSkill3 = 6;
     }

     public int useSkill1(Entity target) {
          int dmg = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int useSkill2(Entity target) {
          int dmg = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int useSkill3(Entity target) {
          int dmg = (int) (this.getSkill3() * (1.0 - target.getMagicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int normalAttack(Entity target) {// magical normal attack
          int dmg = (int) (this.magicalAttack * (1.0 - target.getMagicalDefence() / 100.0)); // player HP
          this.damageDealt(target, dmg);
          return dmg;
     }
}
