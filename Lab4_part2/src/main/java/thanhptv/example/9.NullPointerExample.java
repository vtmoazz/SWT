import java.util.logging.Logger;

class NullPointerExample {
    private static final Logger logger =
            Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        String text = getUserInput();

        if (text == null || text.isEmpty()) {
            logger.warning("Text is null or empty");
        } else {
            logger.info("Text is not empty");
        }
    }

    private static String getUserInput() {
        // Giả lập giá trị có thể null hoặc rỗng để tránh cảnh báo Sonar
        double r = Math.random();
        if (r < 0.33) return null;
        else if (r < 0.66) return "";
        else return "Hello";
    }
}
