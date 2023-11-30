package Entitypack.Monsterpack;

public class Witch extends Monster{
    name = "Witch";
        HP = 40;
        MP = 0;
        physicalAttack = 10;
        magicalAttack = 0;
        physicalDefense = 5;
        magicalDefense = 2;
        skillOne = 100; //Fireball
        CDSKillOne = 4;
       
    public void useSkillone(){
       HP = (int)(skillOne * (1.0 - magicalDefence / 100.0)); //enemy HP
    }
}
