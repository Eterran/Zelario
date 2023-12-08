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
       skillOne = 20; //Poison LV5 unlock
       skillTwo = 100; //Fireball LV10 Unlock
       skillThree = 200; //Frost Nova LV30 Unlock & can freeze all opponent
       CDSkillOne = 4;
       CDSkillTwo = 2;
       CDSkillThree = 6;
    }
       public void useSkillOne(Entity target) {
        
        int damageDealt = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
    }

    public void useSkillTwo(Entity target) {
       
         int damageDealt = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0));
        
    }

    public void useSkillThree(Entity target) {
        
         int damageDealt = (int) (this.getSkill3() * (1.0 - target.getMagicalDefence() / 100.0));
        
    }
}
