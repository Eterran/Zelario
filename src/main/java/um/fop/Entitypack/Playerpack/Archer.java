package Entitypack.Playerpack;

import Entitypack.Entity;

public class Archer extends Archetypes {
     public Archer() {
          name = "Archer";
          HP = 240;
          MP = 60;
          physicalAttack = 65;
          magicalAttack = 50;
          physicalDefence = 45;
          magicalDefence = 20;
          skillOne = 50; // "Buff attack for one turn" LV5 unlock
          skillTwo = 100; // Rapid arrow LV10 Unlock
          skillThree = 500; // Trick shot LV30 Unlock
          CDSkillOne = 4;
          CDSkillTwo = 2;
          CDSkillThree = 6;
     }

     public void useSkillOne() {

          this.physicalAttack = (this.getPhysicalAttack() + this.getSkill1());
     }

     public void useSkillTwo(Entity target) {

          int damageDealt = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));

     }

     public void useSkillThree(Entity target) {

          int damageDealt = (int) (this.getSkill3() * (1.0 - target.getPhysicalDefence() / 100.0));

     }
}
