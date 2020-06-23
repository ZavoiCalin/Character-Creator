package exceptions;

public class UsernameNotAvailableException extends Exception{
    public UsernameNotAvailableException()
    {
        super("Boy, this username is ALREADY TAKEN!");
    }
}
