package exceptions;

public class WrongDeletionKeyException extends Exception{
    public WrongDeletionKeyException()
    {
        super("This deletion key is either wrong or does not exist!");
    }
}
