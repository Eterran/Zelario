package Entitypack.Playerpack;

public class Mage extends Archetypes{
    public Mage(){
    name = "Mage";
       HP = 200;
       MP = 150;
       physicalAttack = 30;
       magicalAttack = 100;
       physicalDefense = 20;
       magicalDefense = 30;
       skillOne = 20; //Poison LV5 unlock
       skillTwo = 100; //Fireball LV10 Unlock
       skillThree = 200; //Frost Nova LV30 Unlock & can freeze all opponent
       CDSKillOne = 4;
       CDSkillTwo = 2;
       CDSkillThree = 6;
    }
       public void useSkillOne() {
        
        damageDealt = (int) (getSkill1() * (1.0 - getMagicalDefence() / 100.0));
    }

    public void useSkillTwo() {
       
         damageDealt = (int) (getSkill2() * (1.0 - getMagicalDefence() / 100.0));
        
    }

    public void useSkillThree() {
        
         damageDealt = (int) (getSkill3() * (1.0 - getMagicalDefence() / 100.0));
        
    }
}
