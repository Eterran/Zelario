package Entitypack.Monsterpack;

public class Harpy extends Monster {
    public Harpy(){
      name = "Harpy";
      HP = 60;
      MP = 20;
      physicalAttack = 14;
      magicalAttack = 10;
      physicalDefence = 8;
      magicalDefence = 8;
      skillOne = 150; //Talon strike
      skillTwo = 150; //Wind Gust
      CDSkillOne = 4;
      CDSkillTwo = 6;
      int expDrop = 60;
    }
    public void useSkillOne() {
        int damageDealt = (int) (getSkill1() * (1.0 - getPhysicalDefence() / 100.0)); //player HP
    }
    
    public void useSkilltwo(){
        int damageDealt = (int) (getSkill2() * (1.0 - getMagicalDefence() / 100.0)); //player HP, and add weakend to player
    }
}

