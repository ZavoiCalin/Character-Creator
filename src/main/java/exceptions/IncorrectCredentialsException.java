package exceptions;

public class IncorrectCredentialsException extends Exception {
    public IncorrectCredentialsException() {
        super("Boy, you need to gimme BOTH A PASSWORD AND A USERNAME;)");
    }
}
