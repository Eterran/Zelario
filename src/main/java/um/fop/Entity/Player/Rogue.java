package Entity.Player;

public class Rogue extends Archetypes{
     public Entity(String name, int initialHP, int initialMP, int initialPhysicalAttack,
                  int initialMagicalAttack, int initialPhysicalDefense, int initialMagicalDefense, int skill1, int skill2, int CDSkillOne, int CDSkillTwo) {
       String name = "Rogue";
       int HP = 250;
       int MP = 75;
       int physicalAttack = 60;
       int magicalAttack = 40;
       int physicalDefense = 50;
       int magicalDefense = 40;
       int skill1 = 50; //Healing
       int skill2 = 150; //Backstab (stun one round)
       int skill3 = 0; //ShadowStep (immune spell or attack one time)
       int CDSKillOne = 4;
       int CDSkillTwo = 2;
       int CDSkillThree = 6;
         
    }
}
