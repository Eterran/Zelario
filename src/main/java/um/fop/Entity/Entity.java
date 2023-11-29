public class Entity {
     
    private String name;
    private int HP;
    private int MP;
    private int physicalAttack;
    private int magicalAttack;
    private int physicalDefence;
    private int magicalDefence;
    private int skill1;
    private int skill2;
    private int skill3;
    private int CDSkillOne;
    private int CDSkillTwo;
    private int CDSkillThree;

    
    public Entity(String name, int HP, int MP, int physicalAttack,
                  int magicalAttack, int physicalDefence, int magicalDefence,
                  int skill1, int skill2, int skill3, int CDSkillOne, int CDSkillTwo, int CDSkillThree) {
        this.name = name;
        this.HP = HP;
        this.MP = MP;
        this.physicalAttack = physicalAttack;
        this.magicalAttack = magicalAttack;
        this.physicalDefence = physicalDefence;
        this.magicalDefence = magicalDefence;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
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

   public int getphysicalAttack() {
        return physicalAttack;
    }
   
   public int getmagicalAttack() {
        return magicalAttack;
    }
   
   public int getphysicalDefence() {
        return physicalDefence;
    }
   
   public int getmagicalDefence() {
        return magicalDefence;
    }
   
   public int getskil1() {
        return skill1;
    }
   
   public int getskil2() {
        return skill2;
    }
   
   public int getskil3() {
        return skill3;
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
}
