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
          skillOne = 50; // "Cha Cha Cha"
          skillTwo = 200; // Furious strike
          skillThree = 50; // Shield wall (reducing damage taken)
          CDSkillOne = 2;
          CDSkillTwo = 4;
          CDSkillThree = 20;
     }

     public void useSkillOne(Entity target) {
          int damageDealt = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
     }

     public void useSkillTwo(Entity target) {
          int damageDealt = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));

     }

     public void useSkillThree(Entity target) {

          int damageTaken = (int) (damageDealt(target) * this.getSkill3() / 100);
          // Other logic for using Skill Three
     }
}
