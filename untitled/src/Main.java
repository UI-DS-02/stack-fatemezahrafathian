import Exceptions.InvalidParenthesize;
import Exceptions.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        while (!string.equals("exit")){
            try {
                if (validParenthesize(string) && validElements(string) && validOperator(string) ){
                    //convert input to stringBuilder
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=0 ; i< string.length() ; i++){
                        stringBuilder.append(string.charAt(i));
                    }
                    //replace e
                    string=replaceE(stringBuilder);
                    //replace PI
                    string=replacePI(stringBuilder);
                    //split fraze whit ,
                    string=splitFraze(string);
                    //convert fraze to postfix
                    String postfix=infixToPostfix(string);
//                    System.out.println(postfix);
                    System.out.println(calculate(postfix));
                }
            } catch (Exception exception/*InvalidParenthesize | InvalidElement | InvalidFactorialOperand | InvalidOperator exception*/ ) {
                System.out.println(exception.getMessage());
            }
            System.out.println("enter values:");
            string=scanner.nextLine();
        }

    }
    private static double calculate(String postfix) throws InvalidFactorialOperand {
        //split fraze whit ,
        String[] operandAndOperators = postfix.split(",");
        //convert string ti stack
        LinkedStack<String> stack = new LinkedStack<>();
        for (int i=operandAndOperators.length -1 ; i>=0 ; i--){
            stack.push(operandAndOperators[i]);
        }
        //save values
        LinkedStack<Double> tempStack = new LinkedStack<>();

        while (!stack.isEmpty()){
            //add values to tempStack
            while (isNumber(stack.top().charAt(0)) ||(stack.top().charAt(0)=='-'&& stack.top().length()>1)){
                tempStack.push(Double.parseDouble(stack.pop()));
            }
            //check and do operator
            if (stack.top().charAt(0)=='+'){
                tempStack.push(tempStack.pop()+tempStack.pop());
                stack.pop();
            }
            else if (stack.top().charAt(0)=='-' && stack.top().length()==1){
                double a=tempStack.pop();
                double b=tempStack.pop();
                tempStack.push(b-a);
                stack.pop();
            }
            else if (stack.top().charAt(0)=='*'){
                tempStack.push(tempStack.pop()*tempStack.pop());
                stack.pop();
            }
            else if (stack.top().charAt(0)=='/'){
                double a=tempStack.pop();
                double b=tempStack.pop();
                tempStack.push(b/a);
                stack.pop();
            }
            else if (stack.top().charAt(0)=='^'){
                double a=tempStack.pop();
                double b=tempStack.pop();
                tempStack.push(Math.pow(b,a));
                stack.pop();
            }
            else if (stack.top().charAt(0)=='!'){
                if (tempStack.top()-tempStack.top().intValue()!=0)
                    throw new InvalidFactorialOperand();
                int a=tempStack.pop().intValue();
                tempStack.push(factorial(a));
                stack.pop();
            }
        }
        return tempStack.pop();
    }
    private static String infixToPostfix(String s){
        //convert string to stringBuilder
        StringBuilder infix = new StringBuilder();
        for (int i = 0 ; i < s.length() ; i++){
            infix.append(s.charAt(i));
        }
        //save opening parenthesize and operators
        LinkedStack<Character> stack = new LinkedStack<>();
        //save postfix form of input fraze
        StringBuilder postfix = new StringBuilder();
        char character;
        for (int i = 0 ; i< infix.length() ; i++){
            character = infix.charAt(i);
            if(character!=')' && character!='(' && character!='+' && character!='-'  && character!='*'
                    && character!='/' && character!='^' && infix.charAt(i)!='!'){
                postfix.append(character);
            }
            else if ( character=='('){
                stack.push(character);
            }
            else if ( character==')'){
                while (!stack.isEmpty()){
                    char t = stack.pop();
                    if (t != '(')
                        postfix.append(t).append(",");
                    else break;
                }
            }
            else if (character=='-'){
                if (infix.charAt(i + 1) == '(') {
                    infix.insert(i+1,'*');
                    infix.insert(i+1,',');
                    infix.insert(i+1,'1');
                    infix.insert(i+1,'-');
                    if (i-2>=0){
                        if (isNumber(infix.charAt(i-2))){
                            infix.insert(i+1,'+');
                        }
                    }

                }
                else if (i==0){
                    postfix.append(character);
                }else {
                    if (infix.charAt(i-1)=='+'||infix.charAt(i-1)=='-'||infix.charAt(i-1)=='*'||infix.charAt(i-1)=='/'||infix.charAt(i-1)=='^'||infix.charAt(i-1)=='(')
                        postfix.append(character);
                    else if (infix.charAt(i + 1) == '(') {

                        infix.insert(i+1,'*');
                        infix.insert(i+1,',');
                        infix.insert(i+1,'1');
                        infix.insert(i+1,'-');
                        if (isNumber(infix.charAt(i-1))){
                            infix.insert(i+1,',');
                            infix.insert(i+1,'+');

                        }
                    } else {
                        while (!stack.isEmpty()){
                            char t = stack.pop();
                            if (t == '('){
                                stack.push(t);
                                break;
                            }else if (t=='+' || t=='-' || t=='*' || t=='/'
                                    || t=='^' || t=='!'){
                                if (getPriority(t)<getPriority(character)){
                                    stack.push(t);
                                    break;
                                }else

                                    postfix.append(t).append(",");
                            }
                        }
                        stack.push(character);
                    }
                }
            }
            else if (character=='+'  || character=='*' || character=='/' || character=='^' || character=='!'){
                if (stack.isEmpty()){
                    stack.push(character);
                }else {
                    while (!stack.isEmpty()){
                        char t = stack.pop();
                        if (t == '('){
                            stack.push(t);
                            break;
                        }else if (t=='+' || t=='-' || t=='*' || t=='/'
                                || t=='^' || t=='!'){
                            if (getPriority(t)<getPriority(character)){
                                stack.push(t);
                                break;
                            }else

                                postfix.append(t).append(",");
                        }
                    }
                    stack.push(character);
                }
            }
        }
        while (!stack.isEmpty()){
            postfix.append(stack.pop()).append(",");
        }
        return postfix.toString();
    }
    private static String splitFraze(String fraze){
        //add , after than operators and operand
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0 ; i<fraze.length() ; i++){
            stringBuilder.append(fraze.charAt(i));
            if (isNumber(fraze.charAt(i))){
                if (i+1<fraze.length()){
                    if (!isNumber(fraze.charAt(i+1)) && fraze.charAt(i+1)!='.'){
                        stringBuilder.append(",");
                    }
                }else stringBuilder.append(",");
            }

        }
        return stringBuilder.toString();
    }
    public static double factorial(int num) throws InvalidFactorialOperand {
        if (num<0)
            throw new InvalidFactorialOperand();
        if (num >= 1)
            return num * factorial(num - 1);
        else
            return 1;
    }
    private static String replaceE(StringBuilder fraze){
        //get a fraze and replace 2.71 whit e
        for (int i = 0 ; i<fraze.length(); i++){
            if (fraze.charAt(i)=='e'){
                fraze.delete(i,i+1);
                fraze.insert(i,"2.71");
            }
        }
        return fraze.toString();
    }
    private static String replacePI(StringBuilder fraze){
        //get a fraze and replace 3.14 whit PI
        for (int i = 0 ; i<fraze.length(); i++){
            if (fraze.charAt(i)=='P'){
                fraze.delete(i,i+2);
                fraze.insert(i,"3.14");
            }
        }
        return fraze.toString();
    }
    private static boolean isNumber(char character) {
        //if character is number return true
        return character == '0' || character == '1' || character == '2' || character == '3' || character == '4' || character == '5'
                || character == '6' || character == '7' || character == '8' || character == '9';
    }
    private static int getPriority(char c){
        //order : () , ! , ^ , * / , + -
        if (c=='!')
            return 4;
        else if (c=='^')
            return 3;
        else if (c=='*' || c=='/')
            return 2;
        else if (c=='+' || c=='-')
            return 1;
        else
            return 5;
    }
    private static boolean validParenthesize(String fraze) throws InvalidParenthesize {
        //save ( elements
        LinkedStack<Character> opening = new LinkedStack<>();
        //check Parenthesize
        for (int i = 0 ; i<fraze.length(); i++){
            //check opening Parenthesize
            if (fraze.charAt(i)==')'){
                if (opening.isEmpty()) {
                    throw new InvalidParenthesize();
                }
                //delete elements
                opening.pop();
            }
            //add opening Parenthesize
            else if (fraze.charAt(i)=='('){
                opening.push('(');
            }
        }
        if (opening.isEmpty())
            return true;
        throw new InvalidParenthesize();
    }
    private static boolean validElements(String fraze) throws InvalidElement {
        boolean invalidElement=true;
        //check elements by loop
        for (int i = 0 ; i<fraze.length(); i++){
            //valid elements can be ( , ) ,. ,+, - ,* ,/ ,!,^, and numbers , PI , e
            if (fraze.charAt(i)!=')' && fraze.charAt(i)!='(' && fraze.charAt(i)!='+'
                    && fraze.charAt(i)!='-' && fraze.charAt(i)!='*' && fraze.charAt(i)!='/' && fraze.charAt(i)!='^'
                    && fraze.charAt(i)!='!' && fraze.charAt(i)!='.' && fraze.charAt(i)!='0' && fraze.charAt(i)!='1'
                    && fraze.charAt(i)!='2' && fraze.charAt(i)!='3' && fraze.charAt(i)!='4' && fraze.charAt(i)!='5' && fraze.charAt(i)!='6'
                    && fraze.charAt(i)!='7' && fraze.charAt(i)!='8' && fraze.charAt(i)!='9' && fraze.charAt(i)!='e'&& fraze.charAt(i)!='P'){
                throw new InvalidElement(fraze.charAt(i));
            }
            if (fraze.charAt(i)=='P'){
                if (i+1>=fraze.length())
                    throw new InvalidElement(fraze.charAt(i));
                else if (fraze.charAt(i+1)!='I'){
                    throw new InvalidElement(fraze.charAt(i));
                }
                i++;
            }
        }
        return invalidElement;
    }
    private static boolean validOperator(String fraze) throws InvalidOperator {
        boolean validOperator=true;
        //check elements by loop
        for (int i = 0 ; i<fraze.length(); i++){
            if (fraze.charAt(i)==')'){
                //after than closing parenthesize can not be numbers or . or (
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)=='0' || fraze.charAt(i+1)=='1' || fraze.charAt(i+1)=='2' || fraze.charAt(i+1)=='3'
                            || fraze.charAt(i+1)=='4' || fraze.charAt(i+1)=='5' || fraze.charAt(i+1)=='6' || fraze.charAt(i+1)=='7'
                            || fraze.charAt(i+1)=='8' || fraze.charAt(i+1)=='9' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)=='('){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }
                //before than opening parenthesize can not be operator or ( or .
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='+' || fraze.charAt(i-1)=='-' || fraze.charAt(i-1)=='*' || fraze.charAt(i-1)=='/'
                            || fraze.charAt(i-1)=='^' || fraze.charAt(i-1)=='.' || fraze.charAt(i-1)=='('){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }
            }
            else if (fraze.charAt(i)=='('){
                if (i+1<fraze.length()){
                    //after than opening parenthesize can not be operator or . or )
                    if (fraze.charAt(i+1)=='+'  || fraze.charAt(i+1)=='*' || fraze.charAt(i+1)=='/'
                            || fraze.charAt(i+1)=='!' || fraze.charAt(i+1)=='^' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)==')'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }
                //before than closing parenthesize can not be numbers or . or )
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='0' || fraze.charAt(i-1)=='1' || fraze.charAt(i-1)=='2' || fraze.charAt(i-1)=='3'
                            || fraze.charAt(i-1)=='4' || fraze.charAt(i-1)=='5' || fraze.charAt(i-1)=='6' || fraze.charAt(i-1)=='7'
                            || fraze.charAt(i-1)=='8' || fraze.charAt(i-1)=='9' || fraze.charAt(i-1)=='.' || fraze.charAt(i-1)==')'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }
            }
            else if (fraze.charAt(i)=='-'){
                //after than - can not be operator and - can not be last of fraze
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)=='+' || fraze.charAt(i+1)=='*' || fraze.charAt(i+1)=='/' || fraze.charAt(i+1)=='!'
                            || fraze.charAt(i+1)=='^' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)==')'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }else throw new InvalidOperator(fraze.charAt(i));
                //before than - can not be .
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='.'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }
            }
            else if (fraze.charAt(i)=='+'|| fraze.charAt(i)=='*' ||fraze.charAt(i)=='/' || fraze.charAt(i)=='^'){
                if (i+1<fraze.length()){
                    //after than operators can not be another operator or . or ) and can not comes last of fraze
                    if (fraze.charAt(i+1)=='+' || fraze.charAt(i+1)=='*' || fraze.charAt(i+1)=='/' || fraze.charAt(i+1)=='!'
                            || fraze.charAt(i+1)=='^' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)==')'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }else throw new InvalidOperator(fraze.charAt(i));
                //before than operators can not be another operator and can not comes first of fraze
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='+' || fraze.charAt(i-1)=='*' || fraze.charAt(i-1)=='/'
                            || fraze.charAt(i-1)=='^' || fraze.charAt(i-1)=='.' || fraze.charAt(i-1)=='('){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }else throw new InvalidOperator(fraze.charAt(i));
            }
            else if (fraze.charAt(i)=='!'){
                //after then ! just come operators  or anything
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)!='+' && fraze.charAt(i+1)!='-' && fraze.charAt(i+1)!='*' && fraze.charAt(i+1)!='/'
                            && fraze.charAt(i+1)!='!' && fraze.charAt(i+1)!='^' && fraze.charAt(i+1)!=')'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }
                //before than ! just comes number and this can not be firs of fraze
                if (i-1>=0){
                    if (fraze.charAt(i-1)!='0' && fraze.charAt(i-1)!='1' && fraze.charAt(i-1)!='2' && fraze.charAt(i-1)!='3'
                            && fraze.charAt(i-1)!='4' && fraze.charAt(i-1)!='5' && fraze.charAt(i-1)!='6'
                            && fraze.charAt(i-1)!='7' && fraze.charAt(i-1)!='8' && fraze.charAt(i-1)!='9'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }else throw new InvalidOperator(fraze.charAt(i));
            }
            else if (fraze.charAt(i)=='.'){
                //after than . just be numbers
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)!='0' && fraze.charAt(i+1)!='1' && fraze.charAt(i+1)!='2' && fraze.charAt(i+1)!='3'
                            && fraze.charAt(i+1)!='4' && fraze.charAt(i+1)!='5' && fraze.charAt(i+1)!='6'
                            && fraze.charAt(i+1)!='7' && fraze.charAt(i+1)!='8' && fraze.charAt(i+1)!='9'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }else throw new InvalidOperator(fraze.charAt(i));
                //before than . just have numbers
                if (i-1>=0){
                    if (fraze.charAt(i-1)!='0' && fraze.charAt(i-1)!='1' && fraze.charAt(i-1)!='2' && fraze.charAt(i-1)!='3'
                            && fraze.charAt(i-1)!='4' && fraze.charAt(i-1)!='5' && fraze.charAt(i-1)!='6'
                            && fraze.charAt(i-1)!='7' && fraze.charAt(i-1)!='8' && fraze.charAt(i-1)!='9'){
                        throw new InvalidOperator(fraze.charAt(i));
                    }
                }else throw new InvalidOperator(fraze.charAt(i));
            }
        }
        return validOperator;
    }
}