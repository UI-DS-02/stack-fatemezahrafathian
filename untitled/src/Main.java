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
            else if (fraze.charAt(i)=='+' || fraze.charAt(i)!='-' || fraze.charAt(i)!='*' ||fraze.charAt(i)!='/' || fraze.charAt(i)!='^'){
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)=='+' || fraze.charAt(i+1)=='*' || fraze.charAt(i+1)=='/' || fraze.charAt(i+1)=='!'
                            || fraze.charAt(i+1)=='^' || fraze.charAt(i+1)=='.' || fraze.charAt(i+1)==')'){
                        validOperator=false;
                    }
                }else validOperator=false;
                if (i-1>=0){
                    if (fraze.charAt(i-1)=='+' || fraze.charAt(i-1)=='*' || fraze.charAt(i-1)=='/' || fraze.charAt(i-1)=='!'
                            || fraze.charAt(i-1)=='^' || fraze.charAt(i-1)=='.' || fraze.charAt(i-1)=='('){
                        validOperator=false;
                    }
                }else validOperator=false;
            }
            else if (fraze.charAt(i)=='!'){
                if (i+1<fraze.length()){
                    if (fraze.charAt(i+1)!='+' || fraze.charAt(i+1)!='-' || fraze.charAt(i+1)!='*' || fraze.charAt(i+1)!='/'
                            || fraze.charAt(i+1)!='!' || fraze.charAt(i+1)!='^' || fraze.charAt(i+1)!=')'){
                        validOperator=false;
                    }
                }
                if (i-1>=0){
                    if (fraze.charAt(i-1)!='0' || fraze.charAt(i-1)!='1' || fraze.charAt(i-1)!='2' || fraze.charAt(i-1)!='3'
                            || fraze.charAt(i-1)!='4' || fraze.charAt(i-1)!='5' || fraze.charAt(i-1)!='6'
                            || fraze.charAt(i-1)!='7' || fraze.charAt(i-1)!='8' || fraze.charAt(i-1)!='9'){
                        validOperator=false;
                    }
                }else validOperator=false;
            }
            else if (fraze.charAt(i)!='.'){
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
}
