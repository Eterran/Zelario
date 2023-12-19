package Entitypack.Playerpack;

import Entitypack.Entity;

public class Rogue extends Archetypes {
     public Rogue() {
          this.name = "Rogue";
          this.HP = 250;
          this.MP = 75;
          this.physicalAttack = 60;
          this.magicalAttack = 40;
          this.physicalDefence = 50;
          this.magicalDefence = 40;
          this.skillOneName = "Smite";
          this.skillTwoName = "Backstab";
          this.skillThreeName = "ShadowStep";
          this.skill1 = 50; // Smite
          this.skill2 = 150; // Backstab (stun one round)
          this.skill3 = 0; // ShadowStep (immune spell or attack one time)
          this.CDSkill1 = 4;
          this.CDSkill2 = 2;
          this.CDSkill3 = 6;
     }

     public void useSkill1(Entity target, int dmg) {

          dmg = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
     }

     public void useSkill2(Entity target, int dmg) {

          dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);

     }

     public void useSkill3() {
          int damage = this.getSkill3();
          damageTaken(damage);
     }

}
