package Entitypack.Playerpack;
import Entitypack.Entity;
public class Paladin extends Archetypes{
     public Paladin(){
     name = "Paladin";
       HP = 350;
       MP = 100;
       physicalAttack = 70;
       magicalAttack = 60;
       physicalDefence = 80;
       magicalDefence = 60;
       skillOne = 20; //Buff Physical Attack
       skillTwo = 100; //Holy Smite, damage and heal a small amount
       skillThree = 0; //Divine Shield (immune damage 2 round)
       CDSkillOne = 3;
       CDSkillTwo = 3;
       CDSkillThree = 6;
     }
     public void useSkillOne() {
        
        physicalAttack =(this.getPhysicalAttack() + this.getSkill1());
    }

    public void useSkillTwo(Entity target) {
       
         int damageDealt = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
         this.HP += (int) (damageDealt * (30 / 100.0));
        
    }

    public void useSkillThree() {
        
         int damageTaken = this.getSkill3() ;
        
    }
}
