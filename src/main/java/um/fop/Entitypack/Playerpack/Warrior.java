package Entitypack.Playerpack;

public class Warrior extends Archetypes{
    public Warrior(String name, int initialHP, int initialMP, int initialPhysicalAttack,
                  int initialMagicalAttack, int initialPhysicalDefense, int initialMagicalDefense, int skill1, int skill2, int skill3, int CDSkill1, int CDSkill2, int CDSkill3) {
       String name = "Warrior";
       int HP = 250;
       int MP = 75;
       int physicalAttack = 60;
       int magicalAttack = 40;
       int physicalDefense = 50;
       int magicalDefense = 40;
       int skill1 = 50; //Buff Attack
       int skill2 = 150; //Furious strike
       int skill3 = 0; //Shield wall (reducing damage taken)
       int CDSKillOne = 3;
       int CDSkillTwo = 4;
       int CDSkillThree = 6;
         
    }
}
