package SQLpack;

import java.io.*;
import Entitypack.Entity;

public class LoadGame {
    public static GameProgress loadGameProgress(String filePath) {
        // 使用ObjectInputStream反序列化GameProgress对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GameProgress) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
