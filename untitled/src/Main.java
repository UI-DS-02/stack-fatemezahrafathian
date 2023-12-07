import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        System.out.println(validParenthesize(string));
        System.out.println(validElements(string));
        System.out.println(validOperator(string));

        if (validParenthesize(string) && validElements(string) && validOperator(string) ){
            StringBuilder stringBuilder = new StringBuilder();
            for (int i=0 ; i< string.length() ; i++){
                stringBuilder.append(string.charAt(i));
            }
            string=replaceE(stringBuilder);
            string=replacePI(stringBuilder);
            string=splitFraze(string);
            String postfix=infixToPostfix(string);
            System.out.println(postfix);;
            String[] operandAndOperators = postfix.split(",");
            LinkedStack<String> stack = new LinkedStack<>();
            LinkedStack<Double> tempStack = new LinkedStack<>();
            for (int i=operandAndOperators.length -1 ; i>=0 ; i--){
                stack.push(operandAndOperators[i]);
            }
            try {
                while (!stack.isEmpty()){
                    while (isNumber(stack.top().charAt(0)) ||(stack.top().charAt(0)=='-'&& stack.top().length()>1)){
                        tempStack.push(Double.parseDouble(stack.pop()));
                    }
                    if (stack.top().charAt(0)=='+'){
                        tempStack.push(tempStack.pop()+tempStack.pop());
                        stack.pop();
                    }
                    else if (stack.top().charAt(0)=='-' && stack.top().length()==1){
                        double a=tempStack.pop();
                        double b=tempStack.pop();
                        tempStack.push(b-a);
                        stack.pop();
                    }else if (stack.top().charAt(0)=='*'){
                        tempStack.push(tempStack.pop()*tempStack.pop());
                        stack.pop();
                    }else if (stack.top().charAt(0)=='/'){
                        double a=tempStack.pop();
                        double b=tempStack.pop();
                        tempStack.push(b/a);
                        stack.pop();
                    }else if (stack.top().charAt(0)=='^'){
                        double a=tempStack.pop();
                        double b=tempStack.pop();
                        tempStack.push(Math.pow(b,a));
                        stack.pop();
                    }else if (stack.top().charAt(0)=='!'){
                        int a=tempStack.pop().intValue();
                        tempStack.push(factorial(a));
                        stack.pop();
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println(tempStack.pop());
        }
    }
    private static boolean validParenthesize(String fraze){
        //save ) elements
        LinkedStack<Character> opening = new LinkedStack<>();
        //check Parenthesize of loop
        for (int i = 0 ; i<fraze.length(); i++){
            //check opening Parenthesize
            if (fraze.charAt(i)==')'){
                if (opening.isEmpty()) {
                    return false;
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
        return false;
    }
    private static boolean validElements(String fraze){
        boolean invalidElement=true;
        //check elements of loop
        for (int i = 0 ; i<fraze.length(); i++){
            if (fraze.charAt(i)!=')' && fraze.charAt(i)!='(' && fraze.charAt(i)!='+'
                    && fraze.charAt(i)!='-' && fraze.charAt(i)!='*' && fraze.charAt(i)!='/' && fraze.charAt(i)!='^'
                    && fraze.charAt(i)!='!' && fraze.charAt(i)!='.' && fraze.charAt(i)!='0' && fraze.charAt(i)!='1'
                    && fraze.charAt(i)!='2' && fraze.charAt(i)!='3' && fraze.charAt(i)!='4' && fraze.charAt(i)!='5' && fraze.charAt(i)!='6'
                    && fraze.charAt(i)!='7' && fraze.charAt(i)!='8' && fraze.charAt(i)!='9' && fraze.charAt(i)!='e'&& fraze.charAt(i)!='P'){
                invalidElement=false;
            }
            if (fraze.charAt(i)=='P'){
                if (i+1>=fraze.length())
                    invalidElement=false;
                else if (fraze.charAt(i+1)!='I'){
                    invalidElement=false;
                }
                i++;
            }
        }
        return invalidElement;
    }
    private static boolean validOperator(String fraze){
        boolean validOperator=true;
        //check elements of loop
        for (int i = 0 ; i<fraze.length(); i++){
            if (fraze.charAt(i)==')'){
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)=='0' || fraze.charAt(i+1)=='1' || fraze.charAt(i+1)=='2' || fraze.charAt(i+1)=='3'
                            || fraze.charAt(i+1)=='4' || fraze.charAt(i+1)=='5' || fraze.charAt(i+1)=='6' || fraze.charAt(i+1)=='7'
                            || fraze.charAt(i+1)=='8' || fraze.charAt(i+1)=='9' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)=='('){
                        validOperator=false;
                    }
                }
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='+' || fraze.charAt(i-1)=='-' || fraze.charAt(i-1)=='*' || fraze.charAt(i-1)=='/'
                            || fraze.charAt(i-1)=='!' || fraze.charAt(i-1)=='^' || fraze.charAt(i-1)=='.' || fraze.charAt(i-1)=='('){
                        validOperator=false;
                    }
                }
            }
            else if (fraze.charAt(i)=='('){
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)=='+'  || fraze.charAt(i+1)=='*' || fraze.charAt(i+1)=='/'
                            || fraze.charAt(i+1)=='!' || fraze.charAt(i+1)=='^' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)==')'){
                        validOperator=false;
                    }
                }
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='0' || fraze.charAt(i-1)=='1' || fraze.charAt(i-1)=='2' || fraze.charAt(i-1)=='3'
                            || fraze.charAt(i-1)=='4' || fraze.charAt(i-1)=='5' || fraze.charAt(i-1)=='6' || fraze.charAt(i-1)=='7'
                            || fraze.charAt(i-1)=='8' || fraze.charAt(i-1)=='9' || fraze.charAt(i-1)=='.' || fraze.charAt(i-1)==')'){
                        validOperator=false;
                    }
                }
            }
            else if (fraze.charAt(i)=='-'){
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)=='+' || fraze.charAt(i+1)=='*' || fraze.charAt(i+1)=='/' || fraze.charAt(i+1)=='!'
                            || fraze.charAt(i+1)=='^' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)==')'){
                        validOperator=false;
                    }
                }else validOperator=false;
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='.'){
                        validOperator=false;
                    }
                }
            }
            else if (fraze.charAt(i)=='+'|| fraze.charAt(i)=='*' ||fraze.charAt(i)=='/' || fraze.charAt(i)=='^'){
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)=='+' || fraze.charAt(i+1)=='*' || fraze.charAt(i+1)=='/' || fraze.charAt(i+1)=='!'
                            || fraze.charAt(i+1)=='^' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)==')'){
                        validOperator=false;
                    }
                }else validOperator=false;
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='+' || fraze.charAt(i-1)=='*' || fraze.charAt(i-1)=='/'
                            || fraze.charAt(i-1)=='^' || fraze.charAt(i-1)=='.' || fraze.charAt(i-1)=='('){
                        validOperator=false;
                    }
                }else validOperator=false;
            }
            else if (fraze.charAt(i)=='!'){
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)!='+' && fraze.charAt(i+1)!='-' && fraze.charAt(i+1)!='*' && fraze.charAt(i+1)!='/'
                            && fraze.charAt(i+1)!='!' && fraze.charAt(i+1)!='^' && fraze.charAt(i+1)!=')'){
                        validOperator=false;
                    }
                }
                if (i-1>=0){
                    if (fraze.charAt(i-1)!='0' && fraze.charAt(i-1)!='1' && fraze.charAt(i-1)!='2' && fraze.charAt(i-1)!='3'
                            && fraze.charAt(i-1)!='4' && fraze.charAt(i-1)!='5' && fraze.charAt(i-1)!='6'
                            && fraze.charAt(i-1)!='7' && fraze.charAt(i-1)!='8' && fraze.charAt(i-1)!='9'){
                        validOperator=false;
                    }
                }else validOperator=false;
            }
            else if (fraze.charAt(i)=='.'){
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)!='0' && fraze.charAt(i+1)!='1' && fraze.charAt(i+1)!='2' && fraze.charAt(i+1)!='3'
                            && fraze.charAt(i+1)!='4' && fraze.charAt(i+1)!='5' && fraze.charAt(i+1)!='6'
                            && fraze.charAt(i+1)!='7' && fraze.charAt(i+1)!='8' && fraze.charAt(i+1)!='9'){
                        validOperator=false;
                    }
                }else validOperator=false;
                if (i-1>=0){
                    if (fraze.charAt(i-1)!='0' && fraze.charAt(i-1)!='1' && fraze.charAt(i-1)!='2' && fraze.charAt(i-1)!='3'
                            && fraze.charAt(i-1)!='4' && fraze.charAt(i-1)!='5' && fraze.charAt(i-1)!='6'
                            && fraze.charAt(i-1)!='7' && fraze.charAt(i-1)!='8' && fraze.charAt(i-1)!='9'){
                        validOperator=false;
                    }
                }else validOperator=false;
            }
        }
        return validOperator;
    }
    private static String replaceE(StringBuilder fraze){
        for (int i = 0 ; i<fraze.length(); i++){
            if (fraze.charAt(i)=='e'){
                fraze.delete(i,i+1);
                fraze.insert(i,"2.71");
            }
        }
        return fraze.toString();
    }
    private static String replacePI(StringBuilder fraze){
        for (int i = 0 ; i<fraze.length(); i++){
            if (fraze.charAt(i)=='P'){
                fraze.delete(i,i+2);
                fraze.insert(i,"3.14");
            }
        }
        return fraze.toString();
    }
    private static String splitFraze(String fraze){
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
    private static String infixToPostfix(String s){
        StringBuilder infix = new StringBuilder();
        for (int i = 0 ; i < s.length() ; i++){
            infix.append(s.charAt(i));
        }
        LinkedStack<Character> stack = new LinkedStack<>();
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
    private static boolean isNumber(char character) {
        if (character=='0' || character=='1' || character=='2' || character=='3' || character=='4' || character=='5'
                || character=='6' || character=='7' || character=='8' || character=='9' ){
            return true;
        }
        return false;
    }
    private static int getPriority(char c){
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
    public static double factorial(int num)
    {
        if (num >= 1)
            return num * factorial(num - 1);
        else
            return 1;
    }

}
