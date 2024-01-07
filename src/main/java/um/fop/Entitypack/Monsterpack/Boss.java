package Entitypack.Monsterpack;

import Entitypack.*;

public class Boss extends Monster {
    public Boss() {
        super();
        this.name = "Dragon";
        this.HP = 1000;
        this.MP = 200;
        this.maxHP = 1000;
        this.maxMP = 200;
        this.physicalAttack = 40;
        this.magicalAttack = 30;
        this.physicalDefence = 30;
        this.magicalDefence = 30;
        this.skillOneName = "Biu Biu Biu";
        this.skill1Description = "a physical damage strike.";
        this.skillTwoName = "Flame burst";
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
        int dmg = (int) ( this.getPhysicalAttack() * (this.getSkill1()/100) * (3.5 - target.getPhysicalDefence() / 100.0)); // player HP
        return this.damageDealt(target, dmg);
    }

    public int useSkill2(Entity target) {
        setCDSkill2(this.getMaxCDSkill2());
        this.setMP(this.getMP() - this.getSkill2Mp());
        int dmg = (int) (this.getPhysicalAttack() * (this.getSkill2()/100) * (3.1 - target.getMagicalDefence() / 100.0)); // player HP, and add
        target.applyStatus(Status.WEAKENED, 2);
        target.applyStatus(Status.SILENCED, dmg);
        target.applyStatus(Status.CONFUSION, dmg);
        return this.damageDealt(target, dmg);
    }

}
