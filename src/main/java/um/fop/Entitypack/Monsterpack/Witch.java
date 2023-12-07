package Entitypack.Monsterpack;

public class Witch extends Monster{
   public Witch(){
   name = "Witch";
        HP = 40;
        MP = 0;
        physicalAttack = 10;
        magicalAttack = 0;
        physicalDefence = 5;
        magicalDefence = 2;
        skillOne = 100; //Fireball
        CDSKillOne = 4;
   }
   public void useSkillOne(Entity target) {
      
        int damageDealt = (int) (this.getSkill1() * (1.0 - target.getMagicalDefence() / 100.0));
      
    }
}

