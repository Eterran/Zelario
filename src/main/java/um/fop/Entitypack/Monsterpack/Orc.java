package Entitypack.Monsterpack;

public class Orc extends Monster{
    public Orc(){
        super();
        this.name = "Orc";
        this.HP = 70;
        this.MP = 0;
        this.maxHP = this.HP;
        this.maxMP = this.MP;
        this.physicalAttack = 15;
        this.magicalAttack = 0;
        this.physicalDefence = 9;
        this.magicalDefence = 4;
        this.expDrop = 35;
}
}
