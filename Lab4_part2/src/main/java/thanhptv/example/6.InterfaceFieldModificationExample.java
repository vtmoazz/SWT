import java.util.logging.Logger;

class InterfaceFieldModificationExample {
    private static final Logger logger =
            Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public static void main(String[] args) {
        logger.info("Max users allowed: " + ConstantsValues.MAX_USERS);
    }
}

final class ConstantsValues {
    // 1️⃣ Ẩn constructor mặc định để không thể tạo đối tượng của class hằng số
    private ConstantsValues() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    // 2️⃣ Các hằng số công khai, bất biến
    public static final int MAX_USERS = 100;
}
