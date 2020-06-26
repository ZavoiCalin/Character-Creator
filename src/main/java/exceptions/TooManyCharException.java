package exceptions;

public class TooManyCharException extends Exception {
    public TooManyCharException()
    {
        super("You cannot add more than 4 characters!");
    }
}
