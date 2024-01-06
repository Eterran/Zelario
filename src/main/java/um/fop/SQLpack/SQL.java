package SQLpack;
import java.io.File;
import java.io.IOException;
import java.sql.*; // 导入Java SQL包，用于数据库操作

public class SQL {
    // 数据库连接信息
    
    private static final String USER = "root"; // 数据库用户名
    private static final String PASS = "123456"; // 数据库密码
    private static final String DB_URL = "jdbc:sqlite:db\\database.db";
    private static String currentUsername;
    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }
    public static String getCurrentUsername() {
        return currentUsername;
    }
    /**
     * 尝试注册新用户
     * @param conn 数据库连接
     * @param username 用户名
     * @param password 密码
     * @return 注册是否成功
     */
    public static void wipeDatabase(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        String sql = "DELETE FROM YourTableName";
        stmt.executeUpdate(sql);
    }
    public static boolean registerUser(Connection conn, String username, String password) {
        // SQL语句用于插入新用户
        String saveFilePath = "saves/" + username + ".dat";
        File saveFile = new File(saveFilePath);
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch(IOException e) {
                System.out.println("Error creating a new save file.");
            }
        }
        String sql = "INSERT INTO users (username, password, save_file) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, saveFilePath);
            int affectedRows = pstmt.executeUpdate(); // 执行更新并返回影响的行数
            return affectedRows > 0; // 如果影响的行数大于0，则注册成功
        } catch (SQLException e) {
            e.printStackTrace(); // 如果插入出现问题，打印错误轨迹
            return false; // 发生异常时，注册失败
        }
    }
    public static String getSaveFile(Connection conn, String username) throws SQLException {
        String SQL_SELECT = "SELECT save_file FROM users WHERE username = ?";
        PreparedStatement prepstate = conn.prepareStatement(SQL_SELECT);
        prepstate.setString(1, username);
        ResultSet resultSet = prepstate.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("save_file");
        } else {
            return null;
        }
    }
    public static void updateSaveFile(Connection conn, String username, String newSaveFilePath) throws SQLException {
        String SQL_UPDATE = "UPDATE users SET save_file = ? WHERE username = ?";
        PreparedStatement prepstate = conn.prepareStatement(SQL_UPDATE);
        prepstate.setString(1, newSaveFilePath);
        prepstate.setString(2, username);
        int row = prepstate.executeUpdate();
        if (row > 0) {
            System.out.println("Save file path updated successfully for user: " + username);
        } else {
            System.out.println("Failed to update save file path for user: " + username);
        }
    }
    public static void deleteUser(Connection conn, String username) throws SQLException {
        String SQL_DELETE = "DELETE FROM users WHERE username = ?";
        PreparedStatement prepstate = conn.prepareStatement(SQL_DELETE);
        prepstate.setString(1, username);
        int row = prepstate.executeUpdate();
        if (row > 0) {
            System.out.println("User deleted successfully: " + username);
        } else {
            System.out.println("Failed to delete user: " + username);
        }
    }
    public static boolean updatePassword(Connection conn, String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean usernameExists(Connection conn, String username) throws SQLException {
        String SQL_SELECT = "SELECT username FROM users WHERE username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next(); // If resultSet.next() returns true, the username exists
    }
    public static String getPassword(Connection conn, String username) throws SQLException {
        String SQL_SELECT = "SELECT password FROM users WHERE username = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("password");
        } else {
            return null;
        }
    }
    /**
     * 获取数据库连接
     * @return 数据库连接对象
     * @throws SQLException 如果连接失败
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}

