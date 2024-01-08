package SQLpack;

import java.io.*;

public class SaveGame {
    public static void saveGameProgress(String filePath, GameProgress progress) {
        // 使用ObjectOutputStream序列化GameProgress对象
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(progress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}