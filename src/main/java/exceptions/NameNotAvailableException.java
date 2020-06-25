package exceptions;

public class NameNotAvailableException extends Exception{
    public NameNotAvailableException() {
        super("Sorry, but this name has just been taken by someone else.");
    }
}
