package Entitypack.Playerpack;

import Entitypack.*;

public class Archer extends Player {
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
          this.skill1Description = "Buff physical attack for 2 rounds.";
          this.skillTwoName = "Rapid shot";
          this.skill2Description = "Fires a flurry of arrows at the target, dealing moderate damage.";
          this.skillThreeName = "Trick Shot";
          this.skill3Description = "Executes a precise shot that can pierce the enemies, causing heavy damage to the enemy.";
          this.skill1 = 50; // "Buff attack for one turn" LV5 unlock
          this.skill2 = 100; // Rapid arrow LV10 Unlock
          this.skill3 = 300; // Trick shot LV30 Unlock
          this.skill1Mp = 30; 
          this.skill2Mp = 20; 
          this.skill3Mp = 60; 
          this.MaxCDSkill1 = 4;
          this.MaxCDSkill2 = 2;
          this.MaxCDSkill3 = 6;
          this.CDSkill1 = 4;
          this.CDSkill2 = 2;
          this.CDSkill3 = 6;
     }

     public int useSkill1(Entity target) {
          this.setMP(this.getMP() - this.getSkill1Mp());
          this.applyStatus(Status.ARCHERSKILL1BUFF, 2);
          return getSkill1();
     }

     public int useSkill2(Entity target) {
          this.setMP(this.getMP() - this.getSkill2Mp());
          int dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int useSkill3(Entity target) {
          this.setMP(this.getMP() - this.getSkill3Mp());
          int dmg = this.getSkill3() ;
          this.damageDealt(target, dmg);
          return dmg;
     }
}
