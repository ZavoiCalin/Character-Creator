package Exceptions;

public class UsernameNotAvailableException extends Exception{
    public UsernameNotAvailableException()
    {
        super("Boy, this username is ALREADY TAKEN!");
    }
}



