package Entitypack;

import java.util.Map;
import Entitypack.Status;
import java.util.HashMap;

public class Entity {
    protected String name;
    protected int maxHP;
    protected int maxMP;
    protected int HP;
    protected int MP;
    protected int physicalAttack;
    protected int magicalAttack;
    protected int physicalDefence;
    protected int magicalDefence;
    protected int skillOne;
    protected int skillTwo;
    protected int skillThree;
    protected int CDSkillOne;
    protected int CDSkillTwo;
    protected int CDSkillThree;
    // String name, int HP, int MP, int physicalAttack, int magicalAttack, int
    // physicalDefence, int magicalDefence, int skillOne, int skillTwo, int
    // skillThree, int CDSkillOne, int CDSkillTwo, int CDSkillThree

    public Entity() {
        name = "Default Entity";
        maxHP = 100;
        maxMP = 100;
        HP = maxHP;
        MP = maxMP;
        physicalAttack = 1;
        magicalAttack = 1;
        physicalDefence = 1;
        magicalDefence = 1;
        skillOne = 1;
        skillTwo = 1;
        skillThree = 1;
        CDSkillOne = 1;
        CDSkillTwo = 1;
        CDSkillThree = 1;
        this.exp = 1;
        this.level = 0;

        Map<Status, Integer> statuses;

        isFrozen = false;
        isConfused = false;
        isSilenced = false;
        isWeakened = false;

        isSkill1Unlocked = false;
        isSkill2Unlocked = false;
        isSkill3Unlocked = false;
    }

    public String getName() {
        return name;
    }
    public int getMaxHP() {
        return maxHP;
    }
    public int getMaxMP() {
        return maxMP;
    }

    public int getHP() {
        return HP;
    }

    public int getMP() {
        return MP;
    }

    public int getPhysicalAttack() {
        // if weakened
        int temp;
        if (this.isWeakened) {
            temp = (int) (physicalAttack * 0.8);
            return temp;
        }
        return physicalAttack;
    }

    public int getMagicalAttack() {
        return magicalAttack;
    }

    public int getPhysicalDefence() {
        return physicalDefence;
    }

    public int getMagicalDefence() {
        return magicalDefence;
    }

    public int getSkill1() {
        return skillOne;
    }

    public int getSkill2() {
        return skillTwo;
    }

    public int getSkill3() {
        return skillThree;
    }

    public int CDSkillOne() {
        return CDSkillOne;
    }

    public int CDSkillTwo() {
        return CDSkillTwo;
    }

    public int CDSkillThree() {
        return CDSkillThree;
    }

    public int damageDealt(Entity target) {
        int damageDealt = (int) (this.physicalAttack * (1.0 - target.physicalDefence / 100.0));
        
        return damageDealt;
    }

    public int damageTaken(Entity target) {
       int damageTaken = (int) (target.physicalAttack * (1.0 - this.physicalDefence / 100.0));// enemy physical attack
        
        return damageTaken; 
    }

    public void healing() {
        HP += 50;
    }

    public void defend(Entity target) {
        int damageTaken = (int) (damageDealt(target) * (1.0 - this.physicalDefence / 80.0));
        this.HP -= damageTaken;
    }

    public void defaultAttack() {
        
        this.physicalAttack =(this.getPhysicalAttack() - this.getSkill1());
    }
    

    // Statuses & Level
    protected Map<Status, Integer> statuses;
    int exp;
    int level;

    boolean isFrozen;
    boolean isConfused;
    boolean isSilenced;
    boolean isWeakened;

    boolean isSkill1Unlocked;
    boolean isSkill2Unlocked;
    boolean isSkill3Unlocked;

    /*
     * public Entity() {
     * this.statuses = new HashMap<>();
     * isFrozen = false;
     * isConfused = false;
     * isSilenced = false;
     * }
     */

    // Status functions
    public void applyStatus(Status status, int rounds) {
        this.statuses.put(status, rounds);
    }

    public void removeStatus(Status status) {
        this.statuses.remove(status);
    }

    public void clearAllStatus() {
        for (Map.Entry<Status, Integer> entry : statuses.entrySet()) {
            entry.setValue(0);
        }
    }

    public void tickStatus() {
        for (Map.Entry<Status, Integer> entry : statuses.entrySet()) {
            if (statuses.get(entry) > 0)
                entry.setValue(statuses.get(entry) - 1);
        }
    }

    // Status Effects
    public void applyEffects() {
        for (Map.Entry<Status, Integer> entry : statuses.entrySet()) {
            Status status = entry.getKey();
            switch (status) {
                case POISONED:
                    if (statuses.get(status) > 0)
                        this.HP *= 0.95;
                    break;
                case SILENCED:
                    if (statuses.get(status) > 0)
                        this.isSilenced = true;
                    else
                        this.isSilenced = false;
                    break;
                case CONFUSION:
                    if (statuses.get(status) > 0)
                        this.isConfused = true;
                    else
                        this.isConfused = false;
                    break;
                case FROZEN:
                    if (statuses.get(status) > 0)
                        this.isFrozen = true;
                    else
                        this.isFrozen = false;
                    break;
                case WEAKENED:
                    if (statuses.get(status) > 0)
                        this.isWeakened = true;
                    else
                        this.isWeakened = false;
                    break;
            }
        }
    }

    public void gainEXP(Entity defeatedEntity) {
        if (level != 35) {
            this.exp += 30;
        }
    }

    public int levelUp() {
        if (this.exp >= 25 && this.level < 2) {
            this.level = 2;
            return this.level;
        } else if (this.exp >= 50 && this.level < 3) {
            this.level = 3;
            return this.level;
        } else if (this.exp >= 80 && this.level < 4) {
            this.level = 4;
            return this.level;
        } else if (this.exp >= 110 && this.level < 5) {
            this.level = 5;
            return this.level;
        } else if (this.exp >= 140 && this.level < 6) {
            this.level = 6;
            return this.level;
        } else if (this.exp >= 170 && this.level < 7) {
            this.level = 7;
            return this.level;
        } else if (this.exp >= 210 && this.level < 8) {
            this.level = 8;
            return this.level;
        } else if (this.exp >= 250 && this.level < 9) {
            this.level = 9;
            return this.level;
        } else if (this.exp >= 300 && this.level < 10) {
            this.level = 10;
            return this.level;
        } else if (this.exp >= 350 && this.level < 11) {
            this.level = 11;
            return this.level;
        } else if (this.exp >= 400 && this.level < 12) {
            this.level = 12;
            return this.level;
        } else if (this.exp >= 450 && this.level < 13) {
            this.level = 13;
            return this.level;
        } else if (this.exp >= 500 && this.level < 14) {
            this.level = 14;
            return this.level;
        } else if (this.exp >= 550 && this.level < 15) {
            this.level = 15;
            return this.level;
        } else if (this.exp >= 600 && this.level < 16) {
            this.level = 16;
            return this.level;
        } else if (this.exp >= 650 && this.level < 17) {
            this.level = 17;
            return this.level;
        } else if (this.exp >= 700 && this.level < 18) {
            this.level = 18;
            return this.level;
        } else if (this.exp >= 750 && this.level < 19) {
            this.level = 19;
            return this.level;
        } else if (this.exp >= 800 && this.level < 20) {
            this.level = 20;
            return this.level;
        } else if (this.exp >= 850 && this.level < 21) {
            this.level = 21;
            return this.level;
        } else if (this.exp >= 900 && this.level < 22) {
            this.level = 22;
            return this.level;
        } else if (this.exp >= 950 && this.level < 23) {
            this.level = 23;
            return this.level;
        } else if (this.exp >= 1000 && this.level < 24) {
            this.level = 24;
            return this.level;
        } else if (this.exp >= 1070 && this.level < 25) {
            this.level = 25;
            return this.level;
        } else if (this.exp >= 1150 && this.level < 26) {
            this.level = 26;
            return this.level;
        } else if (this.exp >= 1230 && this.level < 27) {
            this.level = 27;
            return this.level;
        } else if (this.exp >= 1310 && this.level < 28) {
            this.level = 28;
            return this.level;
        } else if (this.exp >= 1400 && this.level < 29) {
            this.level = 29;
            return this.level;
        } else if (this.exp >= 1500 && this.level < 30) {
            this.level = 30;
            return this.level;
        } else if (this.exp >= 1600 && this.level < 31) {
            this.level = 31;
            return this.level;
        } else if (this.exp >= 1700 && this.level < 32) {
            this.level = 32;
            return this.level;
        } else if (this.exp >= 1800 && this.level < 33) {
            this.level = 33;
            return this.level;
        } else if (this.exp >= 1900 && this.level < 34) {
            this.level = 34;
            return this.level;
        } else if (this.exp >= 2000 && this.level < 35) {
            this.level = 35;
            return this.level;
        }
        return -1;
    }

    public void checkLvl() {
        if (this.level == 5) {
            this.isSkill1Unlocked = true;
        } else if (this.level == 15) {
            this.isSkill2Unlocked = true;
        } else if (this.level == 25) {
            this.isSkill3Unlocked = true;
        }
    }
}
