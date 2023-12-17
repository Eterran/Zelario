package Entitypack.Playerpack;

import Entitypack.Entity;

public class Warrior extends Archetypes {
     public Warrior() {
          name = "Warrior";
          HP = 250;
          MP = 75;
          physicalAttack = 60;
          magicalAttack = 40;
          physicalDefence = 50;
          magicalDefence = 40;
          this.skillOneName = "Cha Cha Cha";
          this.skillTwoName = "Furious Strike";
          this.skillThreeName = "Shield Wall";
          skill1 = 50; // "Cha Cha Cha"
          skill2 = 200; // Furious strike
          skill3 = 50; // Shield wall (reducing damage taken)
          CDSkill1 = 2;
          CDSkill2 = 4;
          CDSkill3 = 20;
     }

     public void useSkill1(Entity target, int dmg) {
          dmg = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
     }

     public void useSkill2(Entity target, int dmg) {
          dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);

     }

     public void useSkill3(Entity target, int dmg) {
          double reductionPercentage = this.getSkill3() / 100.0;
          int reducedDamage = (int) (dmg * (1.0 - reductionPercentage));
          this.damageDealt(target, reducedDamage);
      }
      
}
