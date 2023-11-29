package Entity.Monster;

public class Goblin extends Monster{
    private String monsterName;
    private int HP;
    private int MP;
    private int physicalAttack;
    private int magicalAttack;
    private int physicalDefence;
    private int magicalDefence;

    // Constructor
    public Goblin(String name, int initialHP, int initialMP, int initialPhysicalAttack,
                  int initialMagicalAttack, int initialPhysicalDefence, int initialMagicalDefence) {
        monsterName = name;
        HP = initialHP;
        MP = initialMP;
        physicalAttack = initialPhysicalAttack;
        magicalAttack = initialMagicalAttack;
        physicalDefence = initialPhysicalDefence;
        magicalDefence = initialMagicalDefence;
    }
    
    public void damagedealt(){
       HP = (int)(physicalAttack * (1.0 - physicalDefence / 100.0)); //enemy HP
    }
      
       public void damagetaken(){
       HP = (int)(physicalAttack * (1.0 - physicalDefence / 100.0)); //enemy physical attack
    }
}
