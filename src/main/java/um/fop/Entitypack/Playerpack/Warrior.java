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
          skill1 = 50; // "Cha Cha Cha"
          skill2 = 200; // Furious strike
          skill3 = 50; // Shield wall (reducing damage taken)
          CDSkill1 = 2;
          CDSkill2 = 4;
          CDSkill3 = 20;
     }

     public void useSkill1(Entity target) {
          int damageDealt = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
     }

     public void useSkill2(Entity target) {
          int damageDealt = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));

     }

     public void useSkill3(Entity target) {

          int damageTaken = (int) (damageDealt(target) * this.getSkill3() / 100);
          // Other logic for using Skill Three
     }
}
