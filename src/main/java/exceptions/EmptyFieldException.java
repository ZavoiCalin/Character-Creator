package exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("Boi, enter a username AND a p-word;)");
    }
}