
public class CallableException extends RuntimeException {
    public CallableException(String message, Throwable cause) {
        super(message, cause);
    }

    public CallableException(Exception e) {
        super(e);
    }
}
