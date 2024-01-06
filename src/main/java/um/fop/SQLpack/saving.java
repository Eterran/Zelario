package SQLpack;

import java.io.*;
import Entitypack.Entity;
import Entitypack.Playerpack.*;

class GameProgress implements Serializable {
    private Entity playerEntity;
    public static void main(String[] args) {
        Entity playerEntity = new Entity();
        LoadGame loadGame = new LoadGame();
        GameProgress progress1 = loadGame.loadGameProgress("C:\\Users\\kelvi\\Desktop\\game.dat");
        System.out.println(progress1.toTextFormat());
        System.out.println(playerEntity.getHP());
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

class SaveGame {
    public void saveGameProgress(String filePath, GameProgress progress) {
        // 使用ObjectOutputStream序列化GameProgress对象
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(progress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String textFilePath = filePath + ".txt"; // 文本文件的路径
        try (PrintWriter out = new PrintWriter(textFilePath)) {
            // 获取Entity的属性并保存到文本文件
            out.println(progress.toTextFormat());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class LoadGame {
    public GameProgress loadGameProgress(String filePath) {
        // 使用ObjectInputStream反序列化GameProgress对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
