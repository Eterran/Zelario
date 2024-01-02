package Entitypack.Monsterpack;
import Entitypack.Entity;
public class Monster extends Entity{
   
    public int normalAttack(Entity target) {//physical normal attack for monster
        int dmg = (int) (this.getPhysicalAttack() * (1.0 - target.getPhysicalDefence() / 100.0)); 
        return target.damageTaken(dmg);
    }
}
