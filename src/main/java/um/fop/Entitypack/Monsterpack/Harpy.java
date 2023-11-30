package Entitypack.Monsterpack;

public class Harpy extends Monster {
     name = "Harpy";
       HP = 60;
       MP = 20;
       physicalAttack = 14;
       magicalAttack = 10;
       physicalDefense = 8;
       magicalDefense = 8;
       skillOne = 150; //Talon strike
       skillTwo = 150; //Wind Gust
       CDSkillOne = 4;
       CDSkillTwo = 6;
       
       public void useSkillOne() {
        HP = (int)(skillOne * (1.0 - physicalDefence / 100.0)); //player HP
    }
    
    public void useSkilltwo(){
       HP = (int)(skillOne * (1.0 - magicalDefence / 100.0)); //player HP, and add weakend to player
    }
}
