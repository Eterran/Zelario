package Entitypack.Monsterpack;

import Entitypack.*;

public class Boss extends Monster {
    public Boss() {
        super();
        this.name = "ChapTorTiga";
        this.HP = 600;
        this.MP = 200;
        this.physicalAttack = 80;
        this.magicalAttack = 50;
        this.physicalDefence = 30;
        this.magicalDefence = 30;
        this.skillOneName = "Biu Biu Biu";
        this.skill1Description = "a physical damage strike.";
        this.skillTwoName = "Confusion";
        this.skill2Description = "a magical attack that can disrupt opponents and silenced.";
        this.skill1 = 150; 
        this.skill2 = 250; 
        this.skill1Mp = 40; 
        this.skill2Mp = 100; 
        this.MaxCDSkill1 = 4;
        this.MaxCDSkill2 = 6;
        this.CDSkill1 = 4;
        this.CDSkill2 = 6;
        this.expDrop = 100;
    }

    public int useSkill1(Entity target) {
        setCDSkill1(this.getMaxCDSkill1());
        this.setMP(this.getMP() - this.getSkill1Mp());
        int dmg = (int) (this.getSkill1() * (1.0 - target.getPhysicalDefence() / 100.0)); // player HP
        this.damageDealt(target, dmg);
        return dmg;
    }

    public int useSkill2(Entity target) {
        setCDSkill2(this.getMaxCDSkill2());
        this.setMP(this.getMP() - this.getSkill2Mp());
        int dmg = (int) (this.getSkill2() * (1.0 - target.getMagicalDefence() / 100.0)); // player HP, and add
        this.damageDealt(target, dmg);
        target.applyStatus(Status.WEAKENED, 2);
        target.applyStatus(Status.SILENCED, dmg);
        target.applyStatus(Status.CONFUSION, dmg);
        return dmg;
    }

}
