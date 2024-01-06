package SQLpack;

import java.io.*;
import Entitypack.Entity;
import Entitypack.Playerpack.*;

public class GameProgress implements Serializable {
    private Entity playerEntity;
    public static void main(String[] args) {
        Entity playerEntity = new Entity();
        GameProgress progress1 = LoadGame.loadGameProgress("C:\\Users\\kelvi\\Desktop\\game.dat");
        playerEntity = progress1.getPlayerEntity();
        System.out.println(playerEntity.getName());
    }
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


