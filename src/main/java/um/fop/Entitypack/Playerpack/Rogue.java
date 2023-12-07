package Entitypack.Playerpack;

public class Rogue extends Archetypes{
     public Rogue(){
     name = "Rogue";
       HP = 250;
       MP = 75;
       physicalAttack = 60;
       magicalAttack = 40;
       physicalDefence = 50;
       magicalDefence = 40;
       skillOne = 50; //Smite
       skillTwo = 150; //Backstab (stun one round)
       skillThree = 0; //ShadowStep (immune spell or attack one time)
       CDSkillOne = 4;
       CDSkillTwo = 2;
       CDSkillThree = 6;
     }
    public void useSkillOne() {
        
          int damageDealt = (int) (getSkill1() * (1.0 - getPhysicalDefence() / 100.0));
     }

    public void useSkillTwo() {
       
         int damageDealt = (int) (getSkill2() * (1.0 - getPhysicalDefence() / 100.0));
        
    }

    public void useSkillThree() {
        
         int damageTaken = getSkill3() ;
        
    }
}
