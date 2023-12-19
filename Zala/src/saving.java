import java.io.*;
import Entitypack.Entity;

// 定义游戏进度类
class GameProgress implements Serializable {
    private Entity playerEntity;
    private int xPosition; // 角色的X坐标
    private int yPosition; // 角色的Y坐标

    public GameProgress(Entity playerEntity, int xPosition, int yPosition) {
        this.playerEntity = playerEntity;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    // Getter 和 Setter
    public Entity getPlayerEntity() {
        return playerEntity;
    }

    public void setPlayerEntity(Entity playerEntity) {
        this.playerEntity = playerEntity;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "playerEntity=" + playerEntity +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }
}

// 保存游戏进度
class SaveGame {
    public void saveGameProgress(GameProgress progress, String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(progress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 加载游戏进度
class LoadGame {
    public GameProgress loadGameProgress(String filePath) {
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (GameProgress) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
