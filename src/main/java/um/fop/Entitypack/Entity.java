package Entitypack;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.io.Serializable;

import Entitypack.Monsterpack.*;
import UIpack.ColorAttributes;




    public class Entity implements Serializable {
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
            this.maxHP = -1;
            this.maxMP = -1;
            this.HP = this.maxHP;
            this.MP = this.maxMP;
            this.physicalAttack = -1;
            this.magicalAttack = -1;
            this.physicalDefence = -1;
            this.magicalDefence = -1;
            this.skillOneName = "Default Skill1";
            this.skill1Description = "Default skill 1 description.";
            this.skillTwoName = "Default Skill2";
            this.skill2Description = "Default skill 2 description.";
            this.skillThreeName = "Default Skill3";
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
            this.dialogueDisplayed = new HashMap<>();

            this.isDefending = false;
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

        public int getHP() {
            return this.HP;
        }

        public int getMP() {
            return this.MP;
        }

        public int getMaxHP() {
            return this.maxHP;
        }

        public int getMaxMP() {
            return this.maxMP;
        }

        public int getPhysicalAttack() {
            // if weakened
            int temp = this.physicalAttack;
            if (this.isWeakened) {
                temp = (int) (this.physicalAttack * 0.8);
                if (this.isArcherBuff) temp += getSkill1();
                if (this.isPaladinBuff) temp += getSkill1();
                return temp;
            }
            if (this.isArcherBuff) temp += getSkill1();
            if (this.isPaladinBuff) temp += getSkill1();
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

        public String getSkill1Description() {
            return this.skill1Description;
        }

        public String getSkillTwoName() {
            return this.skillTwoName;
        }

        public String getSkill2Description() {
            return this.skill2Description;
        }

        public String getSkillThreeName() {
            return this.skillThreeName;
        }

        public String getSkill3Description() {
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

        public int damageDealt(Entity target, int dmg) {
            return target.damageTaken(dmg);
        }


        public int normalAttack(Entity target) {//physical normal attack
            int dmg = (int) (this.getPhysicalAttack() * (1.0 - target.getPhysicalDefence() / 100.0) );
            return target.damageTaken(dmg);
        }

        public int damageTaken(int dmg) {
            if (this.isImmune) {
                return 0;
            }
            if (this.isShadowed) {
                this.isShadowed = false;
                this.clearAllStatus();
                return 0;
            }
            if (this.isWarriorBuff) {
                double reductionPercentage = this.getSkill3() / 100.0;
                int reducedDamage = (int) (dmg * (1.0 - reductionPercentage));
                this.HP -= reducedDamage;
                return reducedDamage;
            }
            if (this.isDefending) {
                dmg *= 0.5;
                this.HP -= dmg;
                return dmg;
            }
            this.HP -= dmg;
            return dmg;
        }

        public void setHP(int newHP) {
            HP = newHP;
        }

        public void setCDSkill1(int newCD) {
            CDSkill1 = newCD;
        }

        public void setCDSkill2(int newCD) {
            CDSkill2 = newCD;
        }

        public void setCDSkill3(int newCD) {
            CDSkill3 = newCD;
        }

        public void healing() {
            this.HP += 60;
            if (this.getHP() > this.getMaxHP()) {
                this.setHP(this.getMaxHP());
            } else {
                this.setHP(this.getHP());
            }

        }

        public void defend(Entity target) {
            this.isDefending = true;
            this.applyStatus(Status.DEFEND, 1);
        }

        public void setMP(int newMP) {
            MP = newMP;
        }

        public boolean checkMana(int mana) {
            if (this.getMP() >= mana) {
                return true;
            } else {
                return false;
            }
        }

        public void recoverMana() {
            this.MP += 20;
            if (this.getMP() > this.getMaxMP()) {
                setMP(this.getMaxMP());
            } else {
                setMP(this.getMP());
            }
        }

        public int useSkill1(Entity target) {
            return -1;
        }

        public int useSkill2(Entity target) {
            return -1;
        }

        public int useSkill3(Entity target) {
            return -1;
        }

        public boolean checkMonsterHPChange(int previousHP) {
            if (previousHP > this.getHP()) return true;
            return false;
        }

        // statuses & Level
        protected Map<Status, Integer> statuses;
        protected Map<Status, Boolean> dialogueDisplayed = new HashMap<>();
        protected int exp;
        protected int level;
        public int expDrop;
        protected int statsLevel;

        public boolean isDefending;
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
        public void applyEffects(JTextPane textPane) {
            for (Map.Entry<Status, Integer> entry : this.statuses.entrySet()) {
                Status status = entry.getKey();
                switch (status) {
                    case POISONED:
                        if (statuses.get(status) > 0) {
                            int poisonDMG = (int) (this.getMaxHP() * 0.05);
                            this.HP -= poisonDMG;
                            try {
                                StyledDocument doc = textPane.getStyledDocument();
                                if (this instanceof Monster) {
                                    doc.insertString(doc.getLength(), this.getName() + " is poisoned and takes ", ColorAttributes.WHITE);
                                    doc.insertString(doc.getLength(), poisonDMG + "DMG!\n", ColorAttributes.RED);
                                } else {
                                    doc.insertString(doc.getLength(), "You are poisoned and take ", ColorAttributes.WHITE);
                                    doc.insertString(doc.getLength(), poisonDMG + "DMG!\n", ColorAttributes.RED);
                                }
                            } catch (BadLocationException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case DEFEND:
                        if (statuses.get(status) > 0)
                            this.isDefending = true;
                        else
                            this.isDefending = false;
                        break;
                    case SILENCED:
                        if (statuses.get(status) > 0) {
                            this.isSilenced = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    if (this instanceof Monster) {
                                        doc.insertString(doc.getLength(), this.getName() + " is silenced!\n", ColorAttributes.WHITE);
                                    } else {
                                        doc.insertString(doc.getLength(), "You are silenced, you try to open your mouth but only a muted whisper escapes.\n", ColorAttributes.WHITE);
                                    }
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            }
                            dialogueDisplayed.put(status, true);
                        } else {
                            this.isSilenced = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case CONFUSION:
                        if (statuses.get(status) > 0) {
                            this.isConfused = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    if (this instanceof Monster) {
                                        doc.insertString(doc.getLength(), this.getName() + " is confused!\n", ColorAttributes.WHITE);
                                    } else {
                                        doc.insertString(doc.getLength(), "You are confused, stars seem to fly by faster and faster.\n", ColorAttributes.WHITE);
                                    }
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                dialogueDisplayed.put(status, true);
                            }
                        } else {
                            this.isConfused = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case FROZEN:
                        if (statuses.get(status) > 0) {
                            this.isFrozen = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    if (this instanceof Monster) {
                                        doc.insertString(doc.getLength(), this.getName() + " is frozen!\n", ColorAttributes.WHITE);
                                    } else {
                                        doc.insertString(doc.getLength(), "You are frozen, the cold penetrates deep into your skin.\n", ColorAttributes.WHITE);
                                    }
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                dialogueDisplayed.put(status, true);
                            }
                        } else {
                            this.isFrozen = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case WEAKENED:
                        if (statuses.get(status) > 0) {
                            this.isWeakened = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    if (this instanceof Monster) {
                                        doc.insertString(doc.getLength(), this.getName() + " is weakened!\n", ColorAttributes.WHITE);
                                    } else {
                                        doc.insertString(doc.getLength(), "You are weakened, your movements sluggish and attacks feeble.\n", ColorAttributes.WHITE);
                                    }
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                dialogueDisplayed.put(status, true);
                            }
                        } else {
                            this.isWeakened = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case STUNNED:
                        if (statuses.get(status) > 0) {
                            this.isStunned = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    if (this instanceof Monster) {
                                        doc.insertString(doc.getLength(), this.getName() + " is stunned!\n", ColorAttributes.WHITE);
                                    } else {
                                        doc.insertString(doc.getLength(), "\nYou are stunned, endering you momentarily incapacitated.\n", ColorAttributes.WHITE);
                                    }
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                dialogueDisplayed.put(status, false);
                            }
                        } else {
                            this.isStunned = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case IMMUNITY:
                        if (statuses.get(status) > 0) {
                            this.isImmune = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    if (this instanceof Monster) {
                                        doc.insertString(doc.getLength(), this.getName() + " is immune!\n", ColorAttributes.WHITE);
                                    } else {
                                        doc.insertString(doc.getLength(), "You are immune, a radiant light envelops you, shielding you from harm.\n", ColorAttributes.YELLOW);
                                    }
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                dialogueDisplayed.put(status, true);
                            }
                        } else {
                            this.isImmune = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case SHADOWSTEP:
                        if (statuses.get(status) > 0) {
                            this.isShadowed = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    if (this instanceof Monster) {
                                        doc.insertString(doc.getLength(), this.getName() + " hides into the shadow!\n", ColorAttributes.WHITE);
                                    } else {
                                        doc.insertString(doc.getLength(), "You blend into the shadow, a seamless integration with the darkness.\n", ColorAttributes.LIGHT_GRAY);
                                    }
                                    dialogueDisplayed.put(status, true);
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            this.isShadowed = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case ARCHERSKILL1BUFF:
                        if (statuses.get(status) > 0) {
                            this.isArcherBuff = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "You take a deep breath and focuses deeply on your next shot.\n", ColorAttributes.WHITE);
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                dialogueDisplayed.put(status, true);
                            }
                        } else {
                            this.isArcherBuff = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case PALADINSKILL1BUFF:
                        if (statuses.get(status) > 0) {
                            this.isPaladinBuff = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "You raise your weapon high, channeling a righteous fury within.\n", ColorAttributes.WHITE);
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                dialogueDisplayed.put(status, true);
                            }
                        } else {
                            this.isPaladinBuff = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                    case WARRIORDMGRESIST:
                        if (statuses.get(status) > 0) {
                            this.isWarriorBuff = true;
                            if (!dialogueDisplayed.getOrDefault(status, false)) {
                                try {
                                    StyledDocument doc = textPane.getStyledDocument();
                                    doc.insertString(doc.getLength(), "You plants your feet firmly and ready your shield, a stalwart guardian amidst the chaos.\n", ColorAttributes.WHITE);
                                } catch (BadLocationException e) {
                                    e.printStackTrace();
                                }
                                dialogueDisplayed.put(status, true);
                            }
                        } else {
                            this.isWarriorBuff = false;
                            dialogueDisplayed.put(status, false);
                        }
                        break;
                }
            }
        }

        //Level Functions
        public int getEXPDrop() {
            return this.expDrop;
        }

        public int gainEXP(Entity defeatedEntity) {
            if (level != 35) {
                this.exp += defeatedEntity.getEXPDrop();
                return defeatedEntity.getEXPDrop();
            }
            return 0;
        }

        public int getLevel() {
            return this.level;
        }
        public int getEXP(){
            return this.exp;
        }
        public int getStatsLevel() {
            return this.statsLevel;
        }
        public int setLevel(int level) {
            this.level = level;
            return this.level;
        }
        public int setEXP(int exp) {
            this.exp = exp;
            return this.exp;
        }
        public int levelUp() {
            this.checkLvl();
            this.setLevelStats();
            if (this.exp >= 25 && this.level < 2) {
                this.level = 2;
            } if (this.exp >= 50 && this.level < 3) {
                this.level = 3;
            }  if (this.exp >= 80 && this.level < 4) {
                this.level = 4;
            }  if (this.exp >= 110 && this.level < 5) {
                this.level = 5;
            }  if (this.exp >= 140 && this.level < 6) {
                this.level = 6;
            }  if (this.exp >= 170 && this.level < 7) {
                this.level = 7;
            }  if (this.exp >= 210 && this.level < 8) {
                this.level = 8;
            }  if (this.exp >= 250 && this.level < 9) {
                this.level = 9;
            }  if (this.exp >= 300 && this.level < 10) {
                this.level = 10;
            }  if (this.exp >= 350 && this.level < 11) {
                this.level = 11;
            }  if (this.exp >= 400 && this.level < 12) {
                this.level = 12;
            }  if (this.exp >= 450 && this.level < 13) {
                this.level = 13;
            }  if (this.exp >= 500 && this.level < 14) {
                this.level = 14;
            }  if (this.exp >= 550 && this.level < 15) {
                this.level = 15;
            }  if (this.exp >= 600 && this.level < 16) {
                this.level = 16;
            }  if (this.exp >= 650 && this.level < 17) {
                this.level = 17;
            }  if (this.exp >= 700 && this.level < 18) {
                this.level = 18;
            }  if (this.exp >= 750 && this.level < 19) {
                this.level = 19;
            }  if (this.exp >= 800 && this.level < 20) {
                this.level = 20;
            }  if (this.exp >= 850 && this.level < 21) {
                this.level = 21;
            }  if (this.exp >= 900 && this.level < 22) {
                this.level = 22;
            }  if (this.exp >= 950 && this.level < 23) {
                this.level = 23;
            }  if (this.exp >= 1000 && this.level < 24) {
                this.level = 24;
            }  if (this.exp >= 1070 && this.level < 25) {
                this.level = 25;
            }  if (this.exp >= 1150 && this.level < 26) {
                this.level = 26;
            }  if (this.exp >= 1230 && this.level < 27) {
                this.level = 27;
            }  if (this.exp >= 1310 && this.level < 28) {
                this.level = 28;
            }  if (this.exp >= 1400 && this.level < 29) {
                this.level = 29;
            }  if (this.exp >= 1500 && this.level < 30) {
                this.level = 30;
            }  if (this.exp >= 1600 && this.level < 31) {
                this.level = 31;
            }  if (this.exp >= 1700 && this.level < 32) {
                this.level = 32;
            }  if (this.exp >= 1800 && this.level < 33) {
                this.level = 33;
            }  if (this.exp >= 1900 && this.level < 34) {
                this.level = 34;
            }  if (this.exp >= 2000 && this.level < 35) {
                this.level = 35;
            }
            this.checkLvl();
            this.setLevelStats();
            return this.level;
        }

        public void setLevelStats() {
            String heroType = this.getName();
            Random r = new Random();
            if (heroType.equals("Archer")) {
                for (; this.statsLevel < this.getLevel(); this.statsLevel++) {
                    this.maxHP += 2;
                    this.maxMP += 2;
                    this.physicalAttack += 7;
                    this.magicalAttack += 2;
                    this.physicalDefence += 2;
                    this.magicalDefence += 2;
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(4);
                        if (rand == 0) this.physicalAttack += 1;
                        if (rand == 1) this.magicalAttack += 1;
                        if (rand == 2) this.physicalDefence += 1;
                        if (rand == 3) this.magicalDefence += 1;
                    }
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(2);
                        if (rand == 0) this.maxHP += 1;
                        if (rand == 1) this.maxMP += 1;
                    }
                }
            } else if (heroType.equals("Mage")) {
                for (; this.statsLevel < this.getLevel(); this.statsLevel++) {
                    this.maxHP += 2;
                    this.maxMP += 4;
                    this.physicalAttack += 2;
                    this.magicalAttack += 5;
                    this.physicalDefence += 2;
                    this.magicalDefence += 2;
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(4);
                        if (rand == 0) this.physicalAttack += 1;
                        if (rand == 1) this.magicalAttack += 1;
                        if (rand == 2) this.physicalDefence += 1;
                        if (rand == 3) this.magicalDefence += 1;
                    }
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(2);
                        if (rand == 0) this.maxHP += 1;
                        if (rand == 1) this.maxMP += 1;
                    }
                }
            } else if (heroType.equals("Paladin")) {
                for (; this.statsLevel < this.getLevel(); this.statsLevel++) {
                    this.maxHP += 2;
                    this.maxMP += 2;
                    this.physicalAttack += 5;
                    this.magicalAttack += 3;
                    this.physicalDefence += 4;
                    this.magicalDefence += 2;
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(4);
                        if (rand == 0) this.physicalAttack += 1;
                        if (rand == 1) this.magicalAttack += 1;
                        if (rand == 2) this.physicalDefence += 1;
                        if (rand == 3) this.magicalDefence += 1;
                    }
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(2);
                        if (rand == 0) this.maxHP += 1;
                        if (rand == 1) this.maxMP += 1;
                    }
                }
            } else if (heroType.equals("Rogue")) {
                for (; this.statsLevel < this.getLevel(); this.statsLevel++) {
                    this.maxHP += 2;
                    this.maxMP += 2;
                    this.physicalAttack += 4;
                    this.magicalAttack += 2;
                    this.physicalDefence += 4;
                    this.magicalDefence += 2;
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(4);
                        if (rand == 0) this.physicalAttack += 1;
                        if (rand == 1) this.magicalAttack += 1;
                        if (rand == 2) this.physicalDefence += 1;
                        if (rand == 3) this.magicalDefence += 1;
                    }
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(2);
                        if (rand == 0) this.maxHP += 1;
                        if (rand == 1) this.maxMP += 1;
                    }
                }
            } else if (heroType.equals("Warrior")) {
                for (; this.statsLevel < this.getLevel(); this.statsLevel++) {
                    this.maxHP += 4;
                    this.maxMP += 2;
                    this.physicalAttack += 3;
                    this.magicalAttack += 2;
                    this.physicalDefence += 3;
                    this.magicalDefence += 3;
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(4);
                        if (rand == 0) this.physicalAttack += 1;
                        if (rand == 1) this.magicalAttack += 1;
                        if (rand == 2) this.physicalDefence += 1;
                        if (rand == 3) this.magicalDefence += 1;
                    }
                    for (int i = 0; i < 5; i++) {
                        int rand = r.nextInt(2);
                        if (rand == 0) this.maxHP += 1;
                        if (rand == 1) this.maxMP += 1;
                    }
                }
            }
        }

        public void checkLvl() {
            if (this.level >= 5) {
                this.isSkill1Unlocked = true;
            }
            if (this.level >= 15) {
                this.isSkill2Unlocked = true;
            }
            if (this.level >= 25) {
                this.isSkill3Unlocked = true;
            }
        }

        //CD function
        public void CDDecrement() {
            if (this.getCDSkill1() > 0) this.CDSkill1--;
            if (this.getCDSkill2() > 0) this.CDSkill2--;
            if (this.getCDSkill3() > 0) this.CDSkill3--;
        }
        public void retry(){
            this.setHP(this.getMaxHP()/2);
            this.applyStatus(Status.IMMUNITY, 2);
            this.CDDecrement();
            this.CDDecrement();
            this.CDDecrement();
            this.CDDecrement();
            this.CDDecrement();
            this.CDDecrement();
        }
        public void setSaveName(String name){
            this.name = name;
        }
         public void setSaveMaxHP(int maxHP){
            this.maxHP = maxHP;
        }

        public void setSaveMaxMP(int maxMP){
            this.maxMP = maxMP;
        }

        public void setSaveHP(int hp){
            this.HP = hp;
        }

        public void setSaveMP(int mp){
            this.MP = mp;
        }

        public void setSavePhysicalAttack(int attack){
            this.physicalAttack = attack;
        }

        public void setSaveMagicalAttack(int attack){
            this.magicalAttack = attack;
        }

        public void setSavePhysicalDefence(int defence){
            this.physicalDefence = defence;
        }

        public void setSaveMagicalDefence(int defence){
            this.magicalDefence = defence;
        }

        public void setSaveSkillOneName(String skillname){
            this.skillOneName = skillname;
        }

        public void setSaveSkillOneDescription(String desc){
            this.skill1Description = desc;
        }

        public void setSaveSkillTwoName(String skillname){
            this.skillTwoName = skillname;
        }

        public void setSaveSkillTwoDescription(String desc){
            this.skill2Description = desc;
        }

        public void setSaveSkillThreeName(String skillname){
            this.skillThreeName = skillname;
        }

        public void setSaveSkillThreeDescription(String desc){
            this.skill3Description = desc;
        }
        
        public void setSaveSkill1(int skillDmg){
            this.skill1 = skillDmg;
        }

        public void setSaveSkill2(int skillDmg){
            this.skill2 = skillDmg;
        }

        public void setSaveSkill3(int skillDmg){
            this.skill3 = skillDmg;
        }

        public void setSaveSkill1MP(int skillMP){
            this.skill1Mp = skillMP;
        }

        public void setSaveSkill2MP(int skillMP){
            this.skill2Mp = skillMP;
        }

        public void setSaveSkill3MP(int skillMP){
            this.skill3Mp = skillMP;
        }

        public void setSaveMaxCDSkill1(int cd){
            this.MaxCDSkill1 = cd;
        }

        public void setSaveMaxCDSkill2(int cd){
            this.MaxCDSkill2 = cd;
        }

        public void setSaveMaxCDSkill3(int cd){
            this.MaxCDSkill3 = cd;
        }

        public void setSaveCDSkill1(int cd){
            this.CDSkill1 = cd;
        }

        public void setSaveCDSkill2(int cd){
            this.CDSkill2 = cd;
        }

        public void setSaveCDSkill3(int cd){
            this.CDSkill3 = cd;
        }
        


    }

