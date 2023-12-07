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
        CDSkillOne = 4;
        int expDrop = 55;
     }
     public void useSkillOne() {
        int damageDealt = (int) (getSkill1() * (1.0 - getMagicalDefence() / 100.0));
      
    }
}

