package Entitypack;
import java.util.Map;
import Entitypack.Status;
import java.util.HashMap;

public class Entity {
    private String name;
    private int HP;
    private int MP;
    private int physicalAttack;
    private int magicalAttack;
    private int physicalDefence;
    private int magicalDefence;
    private int skillOne;
    private int skillTwo;
    private int skillThree;
    private int CDSkillOne;
    private int CDSkillTwo;
    private int CDSkillThree;

    
    public Entity(String name, int HP, int MP, int physicalAttack,
                  int magicalAttack, int physicalDefence, int magicalDefence,
                  int skillOne, int skillTwo, int skillThree, int CDSkillOne, int CDSkillTwo, int CDSkillThree) {
        this.name = name;
        this.HP = HP;
        this.MP = MP;
        this.physicalAttack = physicalAttack;
        this.magicalAttack = magicalAttack;
        this.physicalDefence = physicalDefence;
        this.magicalDefence = magicalDefence;
        this.skillOne = skillOne;
        this.skillTwo = skillTwo;
        this.skillThree = skillThree;
        this.CDSkillOne = CDSkillOne;
        this.CDSkillTwo = CDSkillTwo;
        this.CDSkillThree = CDSkillThree;
    }

    
    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public int getMP() {
        return MP;
    }

   public int getPhysicalAttack() {
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
   public void damagedealt(){
       HP -= (int)(physicalAttack * (1.0 - physicalDefence / 100.0)); //enemy HP
    }
      
       public void damagetaken(){
       HP -= (int)(physicalAttack * (1.0 - physicalDefence / 100.0)); //enemy physical attack
    }
       
     public void healing() {
        HP += 50;
    }
     
      public void defence() {
        HP -= (int)(physicalAttack * (1.0 - physicalDefence / 80.0));//enemy physical attack
    }
//Statuses & Level
    protected Map<Status, Integer> statuses;
    int exp;
    int level;

    boolean isFrozen;
    boolean isConfused;
    boolean isSilenced;

    boolean isSkill1Unlocked;
    boolean isSkill2Unlocked;
    boolean isSkill3Unlocked;
    /*public Entity() {
        this.statuses = new HashMap<>();
        isFrozen = false;
        isConfused = false;
        isSilenced = false;
    }*/
//Status functions
    public void applyStatus(Status status, int rounds){
        this.statuses.put(status, rounds);
    }
    public void removeStatus(Status status){
        this.statuses.remove(status);
    }
    public void clearAllStatus(){
        for (Map.Entry<Status, Integer> entry : statuses.entrySet()){
            entry.setValue(0);
        }
    }
    public void tickStatus(){
        for (Map.Entry<Status, Integer> entry : statuses.entrySet()){
            if(statuses.get(entry) > 0) entry.setValue(statuses.get(entry) - 1);
        }
    }
//Status Effects
    public void applyEffects(){
        for (Map.Entry<Status, Integer> entry : statuses.entrySet()){
            Status status = entry.getKey();
            switch(status){
                case POISONED:
                    if(statuses.get(status) > 0)
                        this.HP /= 0.95;
                    break;
                case SILENCED:
                    if(statuses.get(status) > 0)
                        this.isSilenced = true;
                    else this.isSilenced = false;
                    break;
                case CONFUSION:
                    if(statuses.get(status) > 0)
                        this.isConfused = true;
                    else this.isConfused = false;
                    break;
                case FROZEN:
                    if(statuses.get(status) > 0)
                        this.isFrozen = true;
                    else this.isFrozen = false;
                    break;
                case WEAKENED:
                    if(statuses.get(status) > 0)
                        this.
                    break;
            }
        }
    }
    public void gainEXP(){
        if(level != 35) {
            this.exp += 1;
        }
    }
    public void checkLvl(){
        if(this.level == 5) {
            this.isSkill1Unlocked = true;
        } else if(this.level == 15) {
            this.isSkill2Unlocked = true;
        } else if(this.level == 25) {
            this.isSkill3Unlocked = true;
        }
    }
}
