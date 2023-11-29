package Entity.Player;

public class Archer extends Archetypes{
     private String playerName;
    private int HP;
    private int MP;
    private int physicalAttack;
    private int magicalAttack;
    private int physicalDefence;
    private int magicalDefence;
    private int skillOne; // Healing
    private int skillTwo; // Rapid shot
    private int skillThree; // Trick shot
    private int CDSkillOne;
    private int CDSkillTwo;
    private int CDSkillThree;

    public Archer(String name, int initialHP, int initialMP, int initialPhysicalAttack,
                  int initialMagicalAttack, int initialPhysicalDefence, int initialMagicalDefence,
                  int skill1, int skill2, int skill3, int CDSkill1, int CDSkill2, int CDSkill3) {
        playerName = name;
        HP = initialHP;
        MP = initialMP;
        physicalAttack = initialPhysicalAttack;
        magicalAttack = initialMagicalAttack;
        physicalDefence = initialPhysicalDefence;
        magicalDefence = initialMagicalDefence;
        skillOne = skill1;
        skillTwo = skill2;
        skillThree = skill3;
        CDSkillOne = CDSkill1;
        CDSkillTwo = CDSkill2;
        CDSkillThree = CDSkill3;
    }

    public void useSKillOne() {
        HP += skillOne;
    }
    
    public void useSkilltwo(){
       HP = (int)(skillOne * (1.0 - physicalDefence / 100.0)); //enemy HP
    }
    
     public void useSkillThree(){
       HP = (int)(skillTwo * (1.0 - physicalDefence / 100.0)); //enemy HP
    }
     
      public void damagedealt(){
       HP = (int)(physicalAttack * (1.0 - physicalDefence / 100.0)); //enemy HP
    }
      
       public void damagetaken(){
       HP = (int)(physicalAttack * (1.0 - physicalDefence / 100.0)); //enemy physical attack
    }
}
