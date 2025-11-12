import java.util.logging.Logger;


 class HardcodedCredentialsExample {
    private static final Logger logger =
            Logger.getLogger(HardcodedCredentialsExample.class.getName());

    public static void main(String[] args) {
        String username = System.getenv().getOrDefault("ADMIN_USER", "admin");
        String password = System.getenv().getOrDefault("ADMIN_PASS", "");

        if (authenticate(username, password)) {
            logger.info("Access granted");
        } else {
            logger.warning("Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        // Dummy logic: đọc từ ENV để tránh hardcode
        String expectedPass = System.getenv().getOrDefault("ADMIN_PASS", "");
        return user.equals("admin") && pass.equals(expectedPass);
    }
}
