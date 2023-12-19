package Entitypack.Monsterpack;
import Entitypack.Entity;
public class Skeleton extends Monster{
    public Skeleton(){
        super();
        this.name = "Skeleton";
        this.HP = 60;
        this.MP = 0;
        this.physicalAttack = 12;
        this.magicalAttack = 0;
        this.physicalDefence = 7;
        this.magicalDefence = 3;
        this.expDrop = 30;
    }
}
