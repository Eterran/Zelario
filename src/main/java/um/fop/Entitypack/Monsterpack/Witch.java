package Entitypack.Monsterpack;

public class Witch extends Monster{
   public Witch(){
   name = "Witch";
        HP = 40;
        MP = 0;
        physicalAttack = 10;
        magicalAttack = 0;
        physicalDefense = 5;
        magicalDefense = 2;
        skillOne = 100; //Fireball
        CDSKillOne = 4;
       
   public void useSkillOne() {
      
        int damageDealt = (int) (getSkill1() * (1.0 - getMagicalDefence() / 100.0));
      
    }
}
}
