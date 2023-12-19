package Entitypack.Playerpack;

import Entitypack.Entity;

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

     public void useSkill1() {

          physicalAttack = (this.getPhysicalAttack() + this.getSkill1());
     }

     public void useSkill2(Entity target, int dmg) {

          dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.HP += (int) (damageDealt(target, dmg) * (30 / 100.0));
          this.damageDealt(target, dmg);

     }

     public void useSkill3() {
          int damage = this.getSkill3();
          damageTaken(damage);
     }

}
