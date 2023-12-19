package Entitypack;

import java.util.Map;
import java.util.Random;

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
    protected String skillOneName;
    protected String skillTwoName;
    protected String skillThreeName;
    protected int skill1;
    protected int skill2;
    protected int skill3;
    protected int CDSkill1;
    protected int CDSkill2;
    protected int CDSkill3;
    protected int totalStats;
    // String name, int HP, int MP, int physicalAttack, int magicalAttack, int
    // physicalDefence, int magicalDefence, int skill1, int skill2, int
    // skill3, int CDSkill1, int CDSkill2, int CDSkill3

    public Entity() {
        this.name = "Default Entity";
        this.maxHP = 100;
        this.maxMP = 100;
        this.HP = maxHP;
        this.MP = maxMP;
        this.physicalAttack = -1;
        this.magicalAttack = -1;
        this.physicalDefence = -1;
        this.magicalDefence = -1;
        this.skillOneName ="Default Skill1";
        this.skillTwoName ="Default Skill1";
        this.skillThreeName ="Default Skill1";
        this.skill1 = -1;
        this.skill2 = -1;
        this.skill3 = -1;
        this.CDSkill1 = -1;
        this.CDSkill2 = -1;
        this.CDSkill3 = -1;
        this.exp = -1;
        this.level = -1;
        this.expDrop = -1;
        this.statsLevel = level;
        this.totalStats = this.maxHP + this.maxMP + this.physicalAttack + this.magicalAttack + this.physicalDefence 
                        + this.magicalDefence + this.skill1 + this.skill2 + this.skill3;

        Map<Status, Integer> statuses;

        this.isFrozen = false;
        this.isConfused = false;
        this.isSilenced = false;
        this.isWeakened = false;

        this.isSkill1Unlocked = false;
        this.isSkill2Unlocked = false;
        this.isSkill3Unlocked = false;
    }

    public String getName() {
        return this.name;
    }
    public int getMaxHP() {
        return this.maxHP;
    }
    public int getMaxMP() {
        return this.maxMP;
    }
    public int getHP() {
        return this.HP;
    }
    public int getMP() {
        return this.MP;
    }
    public int getPhysicalAttack() {
        // if weakened
        int temp;
        if (this.isWeakened) {
            temp = (int) (this.physicalAttack * 0.8);
            return temp;
        }
        return this.physicalAttack;
    }
    public int getMagicalAttack() {
        return this.magicalAttack;
    }
    public int getPhysicalDefence() {
        return this.physicalDefence;
    }
    public int getMagicalDefence() {
        return this.magicalDefence;
    }
    public String getSkillOneName() {
        return this.skillOneName;
    }
    public String getSkillTwoName() {
        return this.skillTwoName;
    }
    public String getSkillThreeName() {
        return this.skillThreeName;
    }
    public int getSkill1() {
        return this.skill1;
    }
    public int getSkill2() {
        return this.skill2;
    }
    public int getSkill3() {
        return this.skill3;
    }
    public int getCDSkill1() {
        return this.CDSkill1;
    }
    public int getCDSkill2() {
        return this.CDSkill2;
    }
    public int getCDSkill3() {
        return this.CDSkill3;
    }
    public int damageDealt(Entity target, int dmg){   
        return dmg;
    }
    public int normalAttack(Entity target, int dmg) {//physical normal attack
        dmg = (int) (this.physicalAttack * (1.0 - target.physicalDefence / 100.0)); 
        return target.HP - dmg;
    }
   

    public int damageTaken(int dmg) {
        this.HP -= dmg;
        return this.HP;  // Return the updated HP value if needed
    }
    

    public void healing() {
        this.HP += 50;
    }

    public void defend(Entity target, int dmg) {
        int damageTaken = (int) (damageDealt(target, dmg) * (1.0 - this.physicalDefence / 80.0));
        this.HP -= damageTaken;
    } 

    public void defaultAttack() {
        
        this.physicalAttack =(this.getPhysicalAttack() - this.getSkill1());
    }
    
    public void useSkill1(Entity target){}
    public void useSkill2(Entity target){}
    public void useSkill3(Entity target){}

    // statuses & Level
    protected Map<Status, Integer> statuses;
    protected int exp;
    protected int level;
    public int expDrop;
    protected int statsLevel;

    public boolean isFrozen;
    public boolean isConfused;
    public boolean isSilenced;
    public boolean isWeakened;

    public boolean isSkill1Unlocked;
    public boolean isSkill2Unlocked;
    public boolean isSkill3Unlocked;

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
        for (Map.Entry<Status, Integer> entry : this.statuses.entrySet()) {
            entry.setValue(0);
        }
    }
    public void tickStatus() {
        for (Map.Entry<Status, Integer> entry : this.statuses.entrySet()) {
            if (this.statuses.get(entry) > 0)
                entry.setValue(statuses.get(entry) - 1);
        }
    }
// Status Effects
    public void applyEffects() {
        for (Map.Entry<Status, Integer> entry : this.statuses.entrySet()) {
            Status status = entry.getKey();
            switch (status) {
                case POISONED:
                    if (statuses.get(status) > 0)
                        this.HP = (int) (maxHP * 0.95);
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
//Level Functions
    public int getEXPDrop(){
        return this.expDrop;
    }
    public int gainEXP(Entity defeatedEntity) {
        if (level != 35) {
            this.exp += defeatedEntity.getEXPDrop();
            return defeatedEntity.getEXPDrop();
        }
        return 0;
    }
    public int getLevel(){
        return this.level;
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
    public void setLevelStats(){
        String heroType = this.getName();
        Random r = new Random();
        if(heroType.equals("Archer")){
            for(; this.statsLevel < this.getLevel(); this.statsLevel++){
                this.maxHP += 10;
                this.maxMP += 5;
                this.physicalAttack += 25;
                this.magicalAttack += 5;
                this.physicalDefence += 2;
                this.magicalDefence += 1;
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(4);
                    if(rand == 0) this.physicalAttack += 1;
                    if(rand == 1) this.magicalAttack += 1;
                    if(rand == 2) this.physicalDefence += 1;
                    if(rand == 3) this.magicalDefence += 1;
                }
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(2);
                    if(rand == 0)this.maxHP += 1;
                    if(rand == 1)this.maxMP += 1;
                }
            }
        } else if(heroType.equals("Mage")){
            for(; this.statsLevel < this.getLevel(); this.statsLevel++){
                this.maxHP += 9;
                this.maxMP += 17;
                this.physicalAttack += 5;
                this.magicalAttack += 20;
                this.physicalDefence += 1;
                this.magicalDefence += 2;
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(4);
                    if(rand == 0) this.physicalAttack += 1;
                    if(rand == 1) this.magicalAttack += 1;
                    if(rand == 2) this.physicalDefence += 1;
                    if(rand == 3) this.magicalDefence += 1;
                }
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(2);
                    if(rand == 0)this.maxHP += 1;
                    if(rand == 1)this.maxMP += 1;
                }
            }
        } else if(heroType.equals("Paladin")){
            for(; this.statsLevel < this.getLevel(); this.statsLevel++){
                this.maxHP += 5;
                this.maxMP += 5;
                this.physicalAttack += 10;
                this.magicalAttack += 10;
                this.physicalDefence += 2;
                this.magicalDefence += 2;
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(4);
                    if(rand == 0) this.physicalAttack += 1;
                    if(rand == 1) this.magicalAttack += 1;
                    if(rand == 2) this.physicalDefence += 1;
                    if(rand == 3) this.magicalDefence += 1;
                }
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(2);
                    if(rand == 0)this.maxHP += 1;
                    if(rand == 1)this.maxMP += 1;
                }
            }
        } else if(heroType.equals("Rogue")){
            for(; this.statsLevel < this.getLevel(); this.statsLevel++){
                this.maxHP += 6;
                this.maxMP += 6;
                this.physicalAttack += 13;
                this.magicalAttack += 5;
                this.physicalDefence += 5;
                this.magicalDefence += 1;
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(4);
                    if(rand == 0) this.physicalAttack += 1;
                    if(rand == 1) this.magicalAttack += 1;
                    if(rand == 2) this.physicalDefence += 1;
                    if(rand == 3) this.magicalDefence += 1;
                }
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(2);
                    if(rand == 0)this.maxHP += 1;
                    if(rand == 1)this.maxMP += 1;
                }
            }
        } else if(heroType.equals("Warrior")){
            for(; this.statsLevel < this.getLevel(); this.statsLevel++){
                this.maxHP += 25;
                this.maxMP += 5;
                this.physicalAttack += 7;
                this.magicalAttack += 3;
                this.physicalDefence += 4;
                this.magicalDefence += 4;
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(4);
                    if(rand == 0) this.physicalAttack += 1;
                    if(rand == 1) this.magicalAttack += 1;
                    if(rand == 2) this.physicalDefence += 1;
                    if(rand == 3) this.magicalDefence += 1;
                }
                for(int i = 0; i < 5; i++){
                    int rand = r.nextInt(2);
                    if(rand == 0)this.maxHP += 1;
                    if(rand == 1)this.maxMP += 1;
                }
            }
        }
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
//CD function
    public void CDDecrement(){
        if(this.getCDSkill1() > 0) this.CDSkill1--;
        if(this.getCDSkill2() > 0) this.CDSkill2--;
        if(this.getCDSkill3() > 0) this.CDSkill3--;
    }
}
