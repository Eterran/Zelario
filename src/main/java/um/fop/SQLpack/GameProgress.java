package SQLpack;

import java.io.*;
import Entitypack.Entity;

public class GameProgress implements Serializable {
    private Entity playerEntity;
    public GameProgress(Entity playerEntity) {
        this.playerEntity = playerEntity;
    }
    public String toTextFormat() {
        return playerEntity.getName() + "\n" +
                playerEntity.getMaxHP() + "\n" +
                playerEntity.getMaxMP() + "\n" +
                playerEntity.getHP() + "\n" +
                playerEntity.getMP() + "\n" +
                playerEntity.getPhysicalAttack() + "\n" +
                playerEntity.getMagicalAttack() + "\n" +
                playerEntity.getPhysicalDefence() + "\n" +
                playerEntity.getMagicalDefence() + "\n" +
                playerEntity.getLevel() + "\n" +
                playerEntity.getEXP() + "\n";
    }
    public Entity getPlayerEntity() {
        return playerEntity;
    }
}


