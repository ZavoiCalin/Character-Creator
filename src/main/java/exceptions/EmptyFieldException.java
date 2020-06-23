package exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("Boy, you need to gimme BOTH A PASSWORD AND A USERNAME;)");
    }
}