package Entitypack.Monsterpack;

import Entitypack.Entity;

public class Witch extends Monster {
     public Witch() {
          super();
          this.name = "Witch";
          this.HP = 40;
          this.MP = 0;
          this.physicalAttack = 10;
          this.magicalAttack = 0;
          this.physicalDefence = 5;
          this.magicalDefence = 2;
          this.skillOneName = "Fireball";
          this.skill1 = 100; // Fireball
          this.CDSkill1 = 4;
     }

     public void useSkill1(Entity target, int dmg) {

           dmg = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
           this.damageDealt(target, dmg);

     }
     public void magicalAttack(Entity target, int dmg) {//magical normal attack
          dmg = (int) (this.magicalAttack * (1.0 - target.getMagicalDefence() / 100.0)); // player HP
          this.damageDealt(target, dmg);
      }
}
