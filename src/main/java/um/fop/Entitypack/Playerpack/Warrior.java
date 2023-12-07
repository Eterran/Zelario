package Entitypack.Playerpack;

public class Warrior extends Archetypes {
     public Warrior() {
          name = "Warrior";
          HP = 250;
          MP = 75;
          physicalAttack = 60;
          magicalAttack = 40;
          physicalDefence = 50;
          magicalDefence = 40;
          skillOne = 50; // "Justice"
          skillTwo = 200; // Furious strike
          skillThree = 50; // Shield wall (reducing damage taken)
          CDSkillOne = 2;
          CDSkillTwo = 4;
          CDSkillThree = 20;
     }

     public void useSkillOne() {
          physicalAttack = (getPhysicalAttack() + getSkill1());
     }

     public void useSkillTwo() {
          int damageDealt = (int) (getSkill2() * (1.0 - getPhysicalDefence() / 100.0));

     }

     public void useSkillThree() {
          int damageTaken = (int) (damageDealt() * getSkill3() / 100);

     }
}
