import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/interview_simulator";
    private static final String USER = "root";
    private static final String PASSWORD = "root123";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do MySQL nao encontrado no classpath.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
