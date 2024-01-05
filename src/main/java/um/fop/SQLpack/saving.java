package SQLpack;

import java.io.*;
import Entitypack.Entity;


class GameProgress implements Serializable {
    private Entity playerEntity;
    private int xPosition;
    private int yPosition;

    public GameProgress(Entity playerEntity, int xPosition, int yPosition) {
        this.playerEntity = playerEntity;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }


    public String toTextFormat() {
        return "Player Entity: " + playerEntity.getName() + "\n" +
                "HP: " + playerEntity.getHP() + "\n" +
                "MP: " + playerEntity.getMP() + "\n" +
                "Level: " + playerEntity.getLevel() + "\n" +
                "X Position: " + getXPosition() + "\n" +
                "Y Position: " + getYPosition() + "\n" +
                // 新增的属性
                "Name: " + playerEntity.getName() + "\n" +
                "Max HP: " + playerEntity.getMaxHP() + "\n" +
                "Max MP: " + playerEntity.getMaxMP() + "\n" +
                "Physical Attack: " + playerEntity.getPhysicalAttack() + "\n" +
                "Magical Attack: " + playerEntity.getMagicalAttack() + "\n" +
                "Physical Defence: " + playerEntity.getPhysicalDefence() + "\n" +
                "Magical Defence: " + playerEntity.getMagicalDefence();
    }



    public Entity getPlayerEntity() {
        return playerEntity;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
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
