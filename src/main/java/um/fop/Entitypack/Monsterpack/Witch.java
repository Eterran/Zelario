package Entitypack.Monsterpack;

import Entitypack.Entity;

public class Witch extends Monster {
     public Witch() {
          name = "Witch";
          HP = 40;
          MP = 0;
          physicalAttack = 10;
          magicalAttack = 0;
          physicalDefence = 5;
          magicalDefence = 2;
          skill1 = 100; // Fireball
          CDSkill1 = 4;
     }

     public void useSkill1(Entity target, int dmg) {

           dmg = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
           this.damageDealt(target, dmg);

     }
}
