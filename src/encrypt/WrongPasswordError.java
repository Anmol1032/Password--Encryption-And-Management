package encrypt;

public class WrongPasswordError extends Exception {
    public WrongPasswordError(Throwable cause) {
        super(cause);
    }
}
