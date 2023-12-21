package Entitypack;

import java.util.Map;
import java.util.Random;
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
    protected String skill1Description;
    protected String skillTwoName;
    protected String skill2Description;
    protected String skillThreeName;
    protected String skill3Description;
    protected int skill1;
    protected int skill2;
    protected int skill3;
    protected int skill1Mp;
    protected int skill2Mp;
    protected int skill3Mp;
    protected int MaxCDSkill1;
    protected int MaxCDSkill2;
    protected int MaxCDSkill3;
    protected int CDSkill1;
    protected int CDSkill2;
    protected int CDSkill3;
    protected int totalStats;

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
        this.skill1Description = "Default skill 1 description.";
        this.skillTwoName ="Default Skill2";
        this.skill2Description = "Default skill 2 description.";
        this.skillThreeName ="Default Skill3";
        this.skill3Description = "Default skill 3 description.";
        this.skill1 = -1;
        this.skill2 = -1;
        this.skill3 = -1;
        this.skill1Mp = -1;
        this.skill2Mp = -1;
        this.skill3Mp = -1;
        this.MaxCDSkill1 = -1;
        this.MaxCDSkill2 = -1;
        this.MaxCDSkill3 = -1;
        this.CDSkill1 = -1;
        this.CDSkill2 = -1;
        this.CDSkill3 = -1;
        this.exp = -1;
        this.level = -1;
        this.expDrop = -1;
        this.statsLevel = level;
        this.totalStats = this.maxHP + this.maxMP + this.physicalAttack + this.magicalAttack + this.physicalDefence 
                        + this.magicalDefence + this.skill1 + this.skill2 + this.skill3;

        this.statuses = new HashMap<>();

        this.isFrozen = false;
        this.isConfused = false;
        this.isSilenced = false;
        this.isWeakened = false;
        this.isStunned = false;
        this.isImmune = false;
        this.isShadowed = false;
        this.isArcherBuff = false;
        this.isWarriorBuff = false;
        this.isPaladinBuff = false;

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
        int temp = this.physicalAttack;
        if (this.isWeakened) {
            temp = (int) (this.physicalAttack * 0.8);
            if(this.isArcherBuff) temp += getSkill1();
            if(this.isPaladinBuff) temp += getSkill1();
            return temp;
        }
        if(this.isArcherBuff) temp += getSkill1();
        if(this.isPaladinBuff) temp += getSkill1();
        return temp;
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

    public String getSkill1Description(){
        return this.skill1Description;
    }

    public String getSkillTwoName() {
        return this.skillTwoName;
    }

     public String getSkill2Description(){
        return this.skill2Description;
    }

    public String getSkillThreeName() {
        return this.skillThreeName;
    }

     public String getSkill3Description(){
        return this.skill3Description;
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

    public int getSkill1Mp() {
        return this.skill1Mp;
    }

    public int getSkill2Mp() {
        return this.skill2Mp;
    }

    public int getSkill3Mp() {
        return this.skill3Mp;
    }

     public int getMaxCDSkill1() {
        return this.MaxCDSkill1;
    }

    public int getMaxCDSkill2() {
        return this.MaxCDSkill2;
    }

    public int getMaxCDSkill3() {
        return this.MaxCDSkill3;
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
        target.damageTaken(dmg);
        return dmg;
    }


    public int normalAttack(Entity target) {//physical normal attack
        int dmg = (int) (this.getPhysicalAttack() * (1.0 - target.getPhysicalDefence() / 100.0) * 0.2); 
        target.damageTaken(dmg);
        return dmg;
    }

    public int damageTaken(int dmg) {
        if(this.isImmune){
            return 0;
        }
        if(this.isShadowed){
            this.isShadowed = false;
            return 0;
        }
        if(this.isWarriorBuff){
            double reductionPercentage = this.getSkill3() / 100.0;
            int reducedDamage = (int) (dmg * (1.0 - reductionPercentage));
            this.HP -= reducedDamage;
            return reducedDamage;
        }
            this.HP -= dmg;
            return this.HP;  // Return the updated HP value if needed
    }

    public void setHP(int newHP){
        HP = newHP;
    }
    
    public void healing() {
        this.HP +=50;
         if(this.getHP() > this.getMaxHP()){
            this.setHP(this.getMaxHP());
         }
         else{
            this.setHP(this.getHP());
         }
    }


    public void defend(Entity target) {
        this.applyStatus(Status.WARRIORDMGRESIST, 3);
    } 

    public void setMP(int newMP){
        MP = newMP;

    }

    public boolean checkMana(int mana) {
        if (this.getMP() >= mana) {
            return true;
        } else {
            
            return false;
        }
    }

    public void RecoverMana(){
         this.MP +=20;
         if(this.getMP() > this.getMaxMP()){
            setMP(this.getMaxMP());
         }
         else{
            setMP(this.getMP());
         }

    }

    public int useSkill1(Entity target){
        return -1;
    }

    public int useSkill2(Entity target){
        return -1;
    }

    public int useSkill3(Entity target){
        return -1;
    }
    
    public boolean checkMonsterHPChange(int previousHP){
        if(previousHP > this.getHP()) return true;
        return false;
    }
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
    public boolean isStunned;
    public boolean isImmune;
    public boolean isShadowed;
    public boolean isArcherBuff;
    public boolean isWarriorBuff;
    public boolean isPaladinBuff;

    public boolean isSkill1Unlocked;
    public boolean isSkill2Unlocked;
    public boolean isSkill3Unlocked;

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
            Status status = entry.getKey();
            if (this.statuses.get(status) > 0)
                entry.setValue(statuses.get(status) - 1);
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
                case STUNNED:
                    if (statuses.get(status) > 0)
                        this.isStunned = true;
                    else
                        this.isStunned = false;
                    break;
                case IMMUNITY:
                    if (statuses.get(status) > 0)
                        this.isImmune = true;
                    else
                        this.isImmune = false;
                    break;
                case SHADOWSTEP:
                    if (statuses.get(status) > 0)
                        this.isShadowed = true;
                    else
                        this.isShadowed = false;
                    break;
                case ARCHERSKILL1BUFF:
                    if (statuses.get(status) > 0)
                        this.isArcherBuff = true;
                    else
                        this.isArcherBuff = false;
                    break;
                case PALADINSKILL1BUFF:
                    if (statuses.get(status) > 0)
                        this.isPaladinBuff = true;
                    else
                        this.isPaladinBuff = false;
                    break;
                case WARRIORDMGRESIST:
                    if (statuses.get(status) > 0)
                        this.isWarriorBuff = true;
                    else
                        this.isWarriorBuff = false;
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
        } else if (this.exp >= 50 && this.level < 3) {
            this.level = 3;
        } else if (this.exp >= 80 && this.level < 4) {
            this.level = 4;
        } else if (this.exp >= 110 && this.level < 5) {
            this.level = 5;
        } else if (this.exp >= 140 && this.level < 6) {
            this.level = 6;
        } else if (this.exp >= 170 && this.level < 7) {
            this.level = 7;
        } else if (this.exp >= 210 && this.level < 8) {
            this.level = 8;
        } else if (this.exp >= 250 && this.level < 9) {
            this.level = 9;
        } else if (this.exp >= 300 && this.level < 10) {
            this.level = 10;
        } else if (this.exp >= 350 && this.level < 11) {
            this.level = 11;
        } else if (this.exp >= 400 && this.level < 12) {
            this.level = 12;
        } else if (this.exp >= 450 && this.level < 13) {
            this.level = 13;
        } else if (this.exp >= 500 && this.level < 14) {
            this.level = 14;
        } else if (this.exp >= 550 && this.level < 15) {
            this.level = 15;
        } else if (this.exp >= 600 && this.level < 16) {
            this.level = 16;
        } else if (this.exp >= 650 && this.level < 17) {
            this.level = 17;
        } else if (this.exp >= 700 && this.level < 18) {
            this.level = 18;
        } else if (this.exp >= 750 && this.level < 19) {
            this.level = 19;
        } else if (this.exp >= 800 && this.level < 20) {
            this.level = 20;
        } else if (this.exp >= 850 && this.level < 21) {
            this.level = 21;
        } else if (this.exp >= 900 && this.level < 22) {
            this.level = 22;
        } else if (this.exp >= 950 && this.level < 23) {
            this.level = 23;
        } else if (this.exp >= 1000 && this.level < 24) {
            this.level = 24;
        } else if (this.exp >= 1070 && this.level < 25) {
            this.level = 25;
        } else if (this.exp >= 1150 && this.level < 26) {
            this.level = 26;
        } else if (this.exp >= 1230 && this.level < 27) {
            this.level = 27;
        } else if (this.exp >= 1310 && this.level < 28) {
            this.level = 28;
        } else if (this.exp >= 1400 && this.level < 29) {
            this.level = 29;
        } else if (this.exp >= 1500 && this.level < 30) {
            this.level = 30;
        } else if (this.exp >= 1600 && this.level < 31) {
            this.level = 31;
        } else if (this.exp >= 1700 && this.level < 32) {
            this.level = 32;
        } else if (this.exp >= 1800 && this.level < 33) {
            this.level = 33;
        } else if (this.exp >= 1900 && this.level < 34) {
            this.level = 34;
        } else if (this.exp >= 2000 && this.level < 35) {
            this.level = 35;
        }
        this.checkLvl();
        return this.level;
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
