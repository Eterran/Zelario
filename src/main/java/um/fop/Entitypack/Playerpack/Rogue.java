package Entitypack.Playerpack;
import Entitypack.Entity;
public class Rogue extends Archetypes{
     public Rogue(){
     name = "Rogue";
       HP = 250;
       MP = 75;
       physicalAttack = 60;
       magicalAttack = 40;
       physicalDefence = 50;
       magicalDefence = 40;
       skill1 = 50; //Smite
       skill2 = 150; //Backstab (stun one round)
       skill3 = 0; //ShadowStep (immune spell or attack one time)
       CDSkill1 = 4;
       CDSkill2 = 2;
       CDSkill3 = 6;
     }
    public void useSkill1(Entity target) {
        
          int damageDealt = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0));
     }

    public void useSkill2(Entity target) {
       
         int damageDealt = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
        
    }

    public void useSkill3() {
        
         int damageTaken = this.getSkill3() ;
        
    }
}
