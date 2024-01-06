package Entitypack.Monsterpack;
import Entitypack.Entity;
import Entitypack.Status;

import java.util.Random;
public class Monster extends Entity{

    Random effect = new Random();
   
    public int normalAttack(Entity target) {//physical normal attack for monster
        int apply = effect.nextInt(2);

        int dmg = (int) (this.getPhysicalAttack() * (2.0 - target.getPhysicalDefence() / 100.0)); 

        if(apply < 2){
            target.applyStatus(Status.WEAKENED,2);
        }
        return target.damageTaken(dmg);
    }
}
