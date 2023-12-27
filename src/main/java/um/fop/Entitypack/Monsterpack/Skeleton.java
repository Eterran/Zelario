package Entitypack.Monsterpack;

public class Skeleton extends Monster{
    public Skeleton(){
        super();
        this.name = "Skeleton";
        this.HP = 60;
        this.MP = 0;
        this.maxHP = this.HP;
        this.maxMP = this.MP;
        this.physicalAttack = 12;
        this.magicalAttack = 0;
        this.physicalDefence = 7;
        this.magicalDefence = 3;
        this.expDrop = 30;
    }
}
