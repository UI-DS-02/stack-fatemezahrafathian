package Exceptions;

public class InvalidElement extends Exception{
    public InvalidElement(Character massage){
        super("invalid input element is "+massage);
    }
}
