package Entitypack.Playerpack;
import Entitypack.Entity;
public class Mage extends Archetypes{
    public Mage(){
    name = "Mage";
       HP = 200;
       MP = 150;
       physicalAttack = 30;
       magicalAttack = 100;
       physicalDefence = 20;
       magicalDefence = 30;
       skill1 = 20; //Poison LV5 unlock
       skill2 = 100; //Fireball LV10 Unlock
       skill3 = 200; //Frost Nova LV30 Unlock & can freeze all opponent
       CDSkill1 = 4;
       CDSkill2 = 2;
       CDSkill3 = 6;
    }
       public void useSkill1(Entity target, int dmg) {
        
        dmg = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
        this.damageDealt(target, dmg);
    }

    public void useSkill2(Entity target, int dmg) {
       
         dmg = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0));
         this.damageDealt(target, dmg);
        
    }

    public void useSkill3(Entity target, int dmg) {
        
         dmg = (int) (this.getSkill3() * (1.0 - target.getMagicalDefence() / 100.0));
         this.damageDealt(target, dmg);
        
    }
}
