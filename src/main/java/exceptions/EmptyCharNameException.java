package exceptions;

public class EmptyCharNameException extends Exception{
    public EmptyCharNameException() {
    super("Please, in order to create your character, enter BOTH a name, and a deletion key.");
}
}
