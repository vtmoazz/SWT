import java.util.logging.Level;
import java.util.logging.Logger;

class CatchGenericExceptionExample {
    private static final Logger logger =
            Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        // Lấy dữ liệu từ args (có thể null nếu không truyền)
        String s = (args.length > 0) ? args[0] : null;

        // Tránh NPE: kiểm tra null rồi hãy dùng
        if (s == null) {
            logger.warning("Input string 's' is null");
            return;
        }

        // Không cần bắt Exception tổng quát; log dùng built-in formatting
        logger.log(Level.INFO, "Length = {0}", s.length());
    }
}
