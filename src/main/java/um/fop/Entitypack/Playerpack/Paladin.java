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
       skill1 = 20; //Buff Physical Attack
       skill2 = 100; //Holy Smite, damage and heal a small amount
       skill3 = 0; //Divine Shield (immune damage 2 round)
       CDSkill1 = 3;
       CDSkill2 = 3;
       CDSkill3 = 6;
     }
     public void useSkill1() {
        
        physicalAttack =(this.getPhysicalAttack() + this.getSkill1());
    }

    public void useSkill2(Entity target, int dmg) {
       
         dmg = (int) (this.getSkill2() * (1.0 - target.getPhysicalDefence() / 100.0));
         this.HP += (int) (damageDealt(target, dmg) * (30 / 100.0));
         this.damageDealt(target, dmg);
        
    }

    public void useSkill3(Entity target, int dmg) {
        
         dmg = this.getSkill3() ;
         this.damageDealt(target, dmg);
        
    }
}
