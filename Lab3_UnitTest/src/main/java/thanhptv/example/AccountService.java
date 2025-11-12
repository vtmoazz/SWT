package thanhptv.example;



import java.util.regex.Pattern;

public class AccountService {

    // Regex email đơn giản – đủ cho lab (username@domain.tld)
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_REGEX.matcher(email).matches();
    }

    /**
     * Đăng ký hợp lệ khi:
     * - username không rỗng
     * - password dài > 6 ký tự
     * - email hợp lệ
     */
    public boolean registerAccount(String username, String password, String email) {
        if (username == null || username.trim().isEmpty()) return false;
        if (password == null || password.length() <= 6) return false;
        return isValidEmail(email);
    }
}


