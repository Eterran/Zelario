package Entitypack.Playerpack;

import Entitypack.*;

public class Paladin extends Archetypes {
     public Paladin() {
          this.name = "Paladin";
          this.HP = 350;
          this.MP = 100;
          this.physicalAttack = 70;
          this.magicalAttack = 60;
          this.physicalDefence = 80;
          this.magicalDefence = 60;
          this.skillOneName = "Rage";
          this.skillTwoName = "Holy Smite";
          this.skillThreeName = "Divine Shield";
          this.skill1 = 20; // Buff Physical Attack
          this.skill2 = 100; // Holy Smite, damage and heal a small amount
          this.skill3 = 0; // Divine Shield (immune damage 2 round)
          this.CDSkill1 = 3;
          this.CDSkill2 = 3;
          this.CDSkill3 = 6;
     }
     public int useSkill1(Entity target) {
          this.applyStatus(Status.PALADINSKILL1BUFF, 3);
          return getSkill1();
     }
     public int useSkill2(Entity target) {
          int dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.HP += (int) (damageDealt(target, dmg) * (30 / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }
     public int useSkill3(Entity target) {
          int dmg = this.getSkill3();
          target.damageTaken(dmg);
          return dmg;
     }
}
