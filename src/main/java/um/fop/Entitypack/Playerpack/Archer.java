package Entitypack.Playerpack;

public class Archer extends Archetypes{
     public Archer(){
     name = "Archer";
        HP = 240;
        MP = 60;
        physicalAttack = 65;
        magicalAttack = 50;
        physicalDefense = 45;
        magicalDefense = 20;
        skillOne = 50; // "Buff attack for one turn" LV5 unlock
        skillTwo = 100; //Rapid arrow LV10 Unlock
        skillThree = 500; //Trick shot LV30 Unlock 
        CDSKillOne = 4;
        CDSkillTwo = 2;
        CDSkillThree = 6;
     }


    public void useSkillOne() {
        
        physicalAttack =(getPhysicalAttack() + getSkill1());
    }

    public void useSkillTwo() {
       
         damageDealt = (int) (getSkill2() * (1.0 - getPhysicalDefence() / 100.0));
        
    }

    public void useSkillThree() {
        
         damageDealt = (int) (getSkill3() * (1.0 - getPhysicalDefence() / 100.0));
        
    }
}

