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

    // 获取X坐标的方法
    public int getXPosition() {
        return xPosition;
    }

    // 获取Y坐标的方法
    public int getYPosition() {
        return yPosition;
    }
}

// 用于保存游戏进度的类
class SaveGame {
    public void saveGameProgress(String filePath, GameProgress progress) {
        // 使用ObjectOutputStream序列化GameProgress对象
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(progress);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建一个额外的文本文件来保存Entity的属性
        String textFilePath = filePath + ".txt"; // 文本文件的路径
        try (PrintWriter out = new PrintWriter(textFilePath)) {
            // 获取Entity的属性并保存到文本文件
            out.println(progress.toTextFormat());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 用于加载游戏进度的类
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
