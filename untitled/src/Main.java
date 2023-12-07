public class Main {
    public static void main(String[] args) {

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
                    && fraze.charAt(i)!='7' && fraze.charAt(i)!='8' && fraze.charAt(i)!='9'){
                invalidElement=false;
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
                    if (fraze.charAt(i+1)=='+' || fraze.charAt(i+1)=='-' || fraze.charAt(i+1)=='*' || fraze.charAt(i+1)=='/'
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
            else if (fraze.charAt(i)=='+' || fraze.charAt(i)=='-' || fraze.charAt(i)=='*' ||fraze.charAt(i)=='/' || fraze.charAt(i)=='^'){
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
                    if (fraze.charAt(i+1)!='0' || fraze.charAt(i+1)!='1' || fraze.charAt(i+1)!='2' || fraze.charAt(i+1)!='3'
                            || fraze.charAt(i+1)!='4' || fraze.charAt(i+1)!='5' || fraze.charAt(i+1)!='6'
                            || fraze.charAt(i+1)!='7' || fraze.charAt(i+1)!='8' || fraze.charAt(i+1)!='9'){
                        validOperator=false;
                    }
                }else validOperator=false;
                if (i-1>=0){
                    if (fraze.charAt(i-1)!='0' || fraze.charAt(i-1)!='1' || fraze.charAt(i-1)!='2' || fraze.charAt(i-1)!='3'
                            || fraze.charAt(i-1)!='4' || fraze.charAt(i-1)!='5' || fraze.charAt(i-1)!='6'
                            || fraze.charAt(i-1)!='7' || fraze.charAt(i-1)!='8' || fraze.charAt(i-1)!='9'){
                        validOperator=false;
                    }
                }else validOperator=false;
            }
        }
        return validOperator;
    }
    private static String infixToPostfix(String infix){
        LinkedStack<Character> stack = new LinkedStack<>();
        StringBuilder postfix = new StringBuilder();
        char character;
        for (int i = 0 ; i< infix.length() ; i++){
            character = infix.charAt(i);
            if(character!=')' && character!='(' && character!='+' && character!='-' && character!='*'
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
                        postfix.append(t);
                    else break;
                }
            }else if (character=='+' || character=='-' || character=='*' || character=='/'
                    || character=='^' || character=='!'){
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

                                postfix.append(t);
                        }
                    }
                    stack.push(character);
                }
            }
        }
        while (!stack.isEmpty()){
            postfix.append(stack.pop());
        }
        return postfix.toString();
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
}
