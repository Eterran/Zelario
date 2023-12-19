package Entitypack.Monsterpack;

import Entitypack.*;

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
        this.skillOneName = "Talon Strike";
        this.skillTwoName = "Wind Gust";
        this.skill1 = 150; // Talon strike
        this.skill2 = 150; // Wind Gust
        this.CDSkill1 = 4;
        this.CDSkill2 = 6;
        this.expDrop = 75;
    }

    public int useSkill1(Entity target) {
        int dmg = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0)); // player HP
        this.damageDealt(target, dmg);
        return dmg;
    }

    public int useSkill2(Entity target) {
        int dmg = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0)); // player HP, and add
        this.damageDealt(target, dmg);
        target.applyStatus(Status.WEAKENED, 2);
        return dmg;
    }
   public int normalAttack(Entity target) {//magical normal attack
        int dmg = (int) (this.magicalAttack * (1.0 - target.getMagicalDefence() / 100.0)); // player HP
        this.damageDealt(target, dmg);
        return dmg;
    }
}
