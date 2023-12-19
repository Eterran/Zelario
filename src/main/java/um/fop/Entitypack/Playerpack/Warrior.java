package Entitypack.Playerpack;

import Entitypack.Entity;
import Entitypack.Status;

public class Warrior extends Archetypes {
     public Warrior() {
          this.name = "Warrior";
          this.HP = 250;
          this.MP = 75;
          this.physicalAttack = 60;
          this.magicalAttack = 40;
          this.physicalDefence = 50;
          this.magicalDefence = 40;
          this.skillOneName = "Cha Cha Cha";
          this.skill1Description = "Slash a hit to monster.";
          this.skillTwoName = "Furious Strike";
          this.skill2Description = "Unleashes a powerful attack, dealing heavy damage to the target.";
          this.skillThreeName = "Shield Wall";
          this.skill3Description = "The Warrior creates an impenetrable barrier with their shield, reducing incoming damage for 3 rounds.";
          this.skill1 = 50; // "Cha Cha Cha"
          this.skill2 = 200; // Furious strike
          this.skill3 = 50; // Shield wall (reducing damage taken)
          this.CDSkill1 = 2;
          this.CDSkill2 = 4;
          this.CDSkill3 = 20;
     }

     public int useSkill1(Entity target) {
          int dmg = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int useSkill2(Entity target) {
          int dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int useSkill3(Entity target) {
          this.applyStatus(Status.WARRIORDMGRESIST, 3);
          return getSkill1();
      }
      
}
