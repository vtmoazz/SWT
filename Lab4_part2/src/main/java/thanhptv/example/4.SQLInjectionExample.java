import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class SQLInjectionExample {
    private static final Logger logger =
            Logger.getLogger(SQLInjectionExample.class.getName());

    public static void main(String[] args) {
        String userInput = "' OR '1'='1";
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/demo", "root", "password");
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userInput);
            logger.log(Level.INFO, "Executing query safely: {0}", stmt);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    logger.log(Level.INFO, "User found: {0}", rs.getString("username"));
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error", e);
        }
    }
}
