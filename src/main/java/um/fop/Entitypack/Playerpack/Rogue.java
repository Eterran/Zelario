package Entitypack.Playerpack;

import Entitypack.Entity;
import Entitypack.Status;

public class Rogue extends Player {
     public Rogue() {
          this.name = "Rogue";
          this.HP = 250;
          this.MP = 75;
          this.physicalAttack = 60;
          this.magicalAttack = 40;
          this.physicalDefence = 50;
          this.magicalDefence = 40;
          this.skillOneName = "Smite";
          this.skill1Description = "Slash a hit to monster.";
          this.skillTwoName = "Backstab";
          this.skill2Description = "The Rogue sneaks behind the target, delivering a devastating backstab, causing extra damage and stunning the enemy for 1 round.";
          this.skillThreeName = "ShadowStep";
          this.skill1Description = "The Rogue can evade the next spell or attack from the enemies.";
          this.skill1 = 50; // Smite
          this.skill2 = 150; // Backstab (stun one round)
          this.skill3 = 0; // ShadowStep (immune spell or attack one time)
          this.CDSkill1 = 4;
          this.CDSkill2 = 2;
          this.CDSkill3 = 6;
     }

     public int useSkill1(Entity target) {

          int dmg = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int useSkill2(Entity target) {
          int dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          target.applyStatus(Status.STUNNED, 1);
          return dmg;
     }

     public int useSkill3(Entity target) {
          this.applyStatus(Status.SHADOWSTEP, 9);
          return this.skill3;
     }

}
