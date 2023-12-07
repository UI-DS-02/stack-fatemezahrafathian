package Exceptions;

public class InvalidOperator extends Exception{
    public InvalidOperator(Character massage ) {
        super("the elements that inputs for operator is invalid. operator : "+massage);
    }
}
