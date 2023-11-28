package Entity.Player;

public class Archer extends Archetypes{
     public Entity(String name, int initialHP, int initialMP, int initialPhysicalAttack,
                  int initialMagicalAttack, int initialPhysicalDefense, int initialMagicalDefense, int skill1, int skill2, int CDSkillOne, int CDSkillTwo) {
       String name = "Archer";
       int HP = 240;
       int MP = 60;
       int physicalAttack = 65;
       int magicalAttack = 50;
       int physicalDefense = 45;
       int magicalDefense = 20;
       int skill1 = 50; //Healing
       int skill2 = 100; //Rapid shot
       int skill3 = 300; //Trick shot
       int CDSKillOne = 4;
       int CDSkillTwo = 2;
       int CDSkillThree = 6;
    }
}
