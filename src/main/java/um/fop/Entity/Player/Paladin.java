package Entity.Player;

public class Paladin extends Archetypes{
     public Entity(String name, int initialHP, int initialMP, int initialPhysicalAttack,
                  int initialMagicalAttack, int initialPhysicalDefense, int initialMagicalDefense, int skill1, int skill2, int CDSkillOne, int CDSkillTwo) {
       String name = "Paladin";
       int HP = 350;
       int MP = 100;
       int physicalAttack = 70;
       int magicalAttack = 60;
       int physicalDefense = 80;
       int magicalDefense = 60;
       int skill1 = 50; //Buff Physical Attack
       int skill2 = 100; //Holy Smite
       int skill3 = 1000; //Divine Shield (immune damage 2 round)
       int CDSKillOne = 3;
       int CDSkillTwo = 3;
       int CDSkillThree = 6;
    }
}
