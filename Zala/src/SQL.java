import java.sql.*; // 导入Java SQL包，用于数据库操作

public class SQL {
    // 数据库连接信息
    private static final String USER = "root"; // 数据库用户名
    private static final String PASS = "123456"; // 数据库密码
    private static final String DB_URL = "jdbc:mysql://localhost:3306/austin?useSSL=false&allowPublicKeyRetrieval=true";

    /**
     * 尝试注册新用户
     * @param conn 数据库连接
     * @param username 用户名
     * @param password 密码
     * @return 注册是否成功
     */
    public static boolean registerUser(Connection conn, String username, String password) {
        // SQL语句用于插入新用户
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int affectedRows = pstmt.executeUpdate(); // 执行更新并返回影响的行数
            return affectedRows > 0; // 如果影响的行数大于0，则注册成功
        } catch (SQLException e) {
            e.printStackTrace(); // 如果插入出现问题，打印错误轨迹
            return false; // 发生异常时，注册失败
        }
    }

    /**
     * 获取数据库连接
     * @return 数据库连接对象
     * @throws SQLException 如果连接失败
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
