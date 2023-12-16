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
          this.skillOneName = "Precise";
          this.skillTwoName = "Rapid Arrow";
          this.skillThreeName = "Trick Shot";
          skill1 = 50; // "Buff attack for one turn" LV5 unlock
          skill2 = 100; // Rapid arrow LV10 Unlock
          skill3 = 500; // Trick shot LV30 Unlock
          CDSkill1 = 4;
          CDSkill2 = 2;
          CDSkill3 = 6;
     }

     public void useSkill1() {

          this.physicalAttack = (this.getPhysicalAttack() + this.getSkill1());
     }

     public void useSkill2(Entity target, int dmg) {

          dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);

     }

     public void useSkill3(Entity target, int dmg) {

          dmg = (int) (this.getSkill3() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);

     }
}
