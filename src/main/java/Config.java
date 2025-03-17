import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/author_book";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "mysecurepassword";

    static {
        loadDataBaseDriver();
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to Database Successfully");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    private static void loadDataBaseDriver() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Database driver loaded successfully");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load database driver", e);
        }
    }
}
