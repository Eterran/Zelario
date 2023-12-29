
import java.io.*; // 导入Java I/O库中所有的类，用于文件操作和对象序列化
import Entitypack.Entity; // 从Entitypack包导入Entity类

// 游戏进度类，包含玩家实体和位置信息
class GameProgress implements Serializable {
    private Entity playerEntity; // 玩家实体对象
    private int xPosition; // 玩家的X坐标
    private int yPosition; // 玩家的Y坐标

    // GameProgress的构造方法
    public GameProgress(Entity playerEntity, int xPosition, int yPosition) {
        this.playerEntity = playerEntity; // 设置玩家实体
        this.xPosition = xPosition; // 设置玩家X坐标
        this.yPosition = yPosition; // 设置玩家Y坐标
    }

    // 将游戏进度信息转换为文本格式的方法
    public String toTextFormat() {
        // 使用字符串拼接生成文本格式的游戏进度信息
        return "Player Entity: " + playerEntity.getName() + "\n" +
                "HP: " + playerEntity.getHP() + "\n" +
                "MP: " + playerEntity.getMP() + "\n" +
                "X Position: " + xPosition + "\n" +
                "Y Position: " + yPosition;
    }

    // getter和setter方法
    public Entity getPlayerEntity() { return playerEntity; }
    public int getXPosition() { return xPosition; }
    public int getYPosition() { return yPosition; }
}
}

// 用于保存游戏进度的类
class SaveGame {
    // 将游戏进度保存为文本格式
    public void saveGameProgressAsText(GameProgress progress, String filePath) {
        // 使用try-with-resources语句确保PrintWriter最终会被关闭
        try (PrintWriter out = new PrintWriter(filePath)) {
            out.print(progress.toTextFormat()); // 写入游戏进度到文件
        } catch (IOException e) {
            e.printStackTrace(); // 打印异常信息
        }
    }
}

// 用于加载游戏进度的类
class LoadGame {
    // 从二进制文件加载游戏进度
    public GameProgress loadGameProgress(String filePath) {
        // 使用try-with-resources语句确保输入流最终会被关闭
        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (GameProgress) objectIn.readObject(); // 读取并返回游戏进度对象
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // 打印异常信息
            return null; // 发生异常时返回null
        }
    }
}

