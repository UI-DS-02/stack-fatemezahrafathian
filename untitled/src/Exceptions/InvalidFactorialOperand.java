package Exceptions;

public class InvalidFactorialOperand extends Exception{
    public InvalidFactorialOperand() {
        super("value of factorial operand must be bigger than or equal to 0 and must be subset of (W)numbers ");
    }
}
