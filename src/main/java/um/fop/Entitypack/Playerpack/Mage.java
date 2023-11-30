package Entitypack.Playerpack;

public class Mage extends Archetypes{
     public Entity(String name, int initialHP, int initialMP, int initialPhysicalAttack,
                  int initialMagicalAttack, int initialPhysicalDefense, int initialMagicalDefense, int skill1, int skill2, int CDSkillOne, int CDSkillTwo) {
       String name = "Mage";
       int HP = 200;
       int MP = 150;
       int physicalAttack = 30;
       int magicalAttack = 100;
       int physicalDefense = 20;
       int magicalDefense = 30;
       int skill1 = 50; //Healing LV5 unlock
       int skill2 = 100; //Fireball LV10 Unlock
       int skill3 = 200; //Frost Nova LV30 Unlock & can freeze all opponent
       int CDSKillOne = 4;
       int CDSkillTwo = 2;
       int CDSkillThree = 6;
    }
}
