package Entitypack.Monsterpack;

import Entitypack.Entity;

public class Witch extends Monster {
     public Witch() {
          super();
          this.name = "Witch";
          this.HP = 50;
          this.MP = 80;
          this.physicalAttack = 8;
          this.magicalAttack = 20;
          this.physicalDefence = 6;
          this.magicalDefence = 12;
          this.skillOneName = "Fireball";
          this.skill1Description = "a potent fire-based magical attack.";
          this.skill1 = 100; // Fireball
          this.skill1Mp = 20;
          this.MaxCDSkill1 = 4;
          this.CDSkill1 = 4;
          this.expDrop = 40;
     }

     public int useSkill1(Entity target) {
          this.setMP(this.getMP() - this.getSkill1Mp());
          int dmg = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
          this.damageDealt(target, dmg);
          return dmg;
     }

     public int normalAttack(Entity target) {// magical normal attack
          int dmg = (int) (this.magicalAttack * (1.0 - target.getMagicalDefence() / 100.0) ); // player HP
          this.damageDealt(target, dmg);
          return dmg;
     }
}
