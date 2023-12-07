package Entitypack.Monsterpack;

import Entitypack.Entity;

public class Harpy extends Monster {
    public Harpy() {
        name = "Harpy";
        HP = 60;
        MP = 20;
        physicalAttack = 14;
        magicalAttack = 10;
        physicalDefence = 8;
        magicalDefence = 8;
        skillOne = 150; // Talon strike
        skillTwo = 150; // Wind Gust
        CDSkillOne = 4;
        CDSkillTwo = 6;
    }

    public void useSkillOne(Entity target) {
        int damageDealt = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0)); // player HP
    }

    public void useSkilltwo(Entity target) {
        int damageDealt = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0)); // player HP, and add
                                                                                                 // weakend to player
    }
}
