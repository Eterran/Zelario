package Entitypack.Monsterpack;

import Entitypack.Entity;

public class Harpy extends Monster {
    public Harpy() {
        super();
        this.name = "Harpy";
        this.HP = 60;
        this.MP = 20;
        this.physicalAttack = 14;
        this.magicalAttack = 10;
        this.physicalDefence = 8;
        this.magicalDefence = 8;
        this.skill1 = 150; // Talon strike
        this.skill2 = 150; // Wind Gust
        this.CDSkill1 = 4;
        this.CDSkill2 = 6;
    }

    public void useSkill1(Entity target) {
        int damageDealt = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0)); // player HP
    }

    public void useSkill2(Entity target) {
        int damageDealt = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0)); // player HP, and add
                                                                                                 // weakend to player
    }
}
