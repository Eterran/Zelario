package SQLpack;

import java.io.*;
import Entitypack.Entity;


class GameProgress implements Serializable {
    private Entity playerEntity;


    public GameProgress(Entity playerEntity) {
        this.playerEntity = playerEntity;

    }


    public String toTextFormat() {
        return "Player Entity: " + playerEntity.getName() + "\n" +
                "HP: " + playerEntity.getHP() + "\n" +
                "MP: " + playerEntity.getMP() + "\n" +
                "Level: " + playerEntity.getLevel() + "\n" +
                "Name: " + playerEntity.getName() + "\n" +
                "Max HP: " + playerEntity.getMaxHP() + "\n" +
                "EXP: " + playerEntity.getEXP() + "\n" +
                "Max MP: " + playerEntity.getMaxMP() + "\n" +
                "Physical Attack: " + playerEntity.getPhysicalAttack() + "\n" +
                "Magical Attack: " + playerEntity.getMagicalAttack() + "\n" +
                "Physical Defence: " + playerEntity.getPhysicalDefence() + "\n" +
                "Magical Defence: " + playerEntity.getMagicalDefence();
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
