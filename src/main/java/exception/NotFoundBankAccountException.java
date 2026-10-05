package exception;

public class NotFoundBankAccountException extends Exception {
    public NotFoundBankAccountException() {
        super("Tài khoản không tồn tại.");
    }
}
