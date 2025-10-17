import java.util.logging.Level;
import java.util.logging.Logger;

class OvercatchingExceptionExample {
    private static final Logger logger =
            Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        int[] arr = new int[5];
        int index = 4; // ví dụ chỉ số cần đọc

        if (index >= 0 && index < arr.length) {
            logger.info("arr[" + index + "] = " + arr[index]);
        } else {
            logger.warning("Index out of bounds: " + index + " (length=" + arr.length + ")");
        }
    }
}
