package Entitypack.Playerpack;

import Entitypack.Entity;

public class Archer extends Archetypes {
     public Archer() {
          super();
          this.name = "Archer";
          this.HP = 240;
          this.MP = 60;
          this.physicalAttack = 65;
          this.magicalAttack = 50;
          this.physicalDefence = 45;
          this.magicalDefence = 20;
          this.skillOneName = "Precise";
          this.skillTwoName = "Rapid Arrow";
          this.skillThreeName = "Trick Shot";
          this.skill1 = 50; // "Buff attack for one turn" LV5 unlock
          this.skill2 = 100; // Rapid arrow LV10 Unlock
          this.skill3 = 500; // Trick shot LV30 Unlock
          this.CDSkill1 = 4;
          this.CDSkill2 = 2;
          this.CDSkill3 = 6;
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
