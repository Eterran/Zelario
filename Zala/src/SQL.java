import java.sql.*; // 导入Java SQL包，用于数据库操作
import java.util.Scanner; // 导入Scanner类，用于读取控制台输入

public class SQL {
    // 数据库连接信息
    private static final String USER = "root"; // 数据库用户名
    private static final String PASS = "123456"; // 数据库密码
    private static final String DB_URL = "jdbc:mysql://localhost:3306/austin?useSSL=false&allowPublicKeyRetrieval=true";

    public static void main(String[] args) {
        // 通过try-with-resources语句来自动关闭资源
        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Welcome to game account registration!");

            // 提示用户输入用户名和密码
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            // 尝试注册用户
            if (registerUser(conn, username, password)) {
                System.out.println("Registration successful!");
            } else {
                System.out.println("Registration failed, username may be taken.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 如果连接或查询出现问题，打印错误轨迹
        }
    }

    private static boolean registerUser(Connection conn, String username, String password) {
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

}

