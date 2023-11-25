package Entity;


public class Entity {
     public Entity(int HP, int MP, int physicalAttack,
                 int magicalAttack, int physicalDefense, int magicalDefense,
                 int skill1, int skill2, int CDSkillOne, int CDSkillTwo) {
        this.HP = HP;
        this.MP = MP;
        this.physicalAttack = physicalAttack;
        this.magicalAttack = magicalAttack;
        this.physicalDefense = physicalDefense;
        this.magicalDefense = magicalDefense;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.CDSkillOne = CDSkillOne;
        this.CDSkillTwo = CDSkillTwo;
    }
}
