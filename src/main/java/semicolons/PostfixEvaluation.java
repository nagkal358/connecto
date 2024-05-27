package semicolons;

import java.util.Stack;

public class PostfixEvaluation {
    public static void main(String[] args) {
        String exp = "231*+9-";

        // Function call
        System.out.println("postfix evaluation: "
                + postfixEvaluation(exp));
    }
    public static int postfixEvaluation(String exp){
        int result = 0;
        Stack<Integer> sta = new Stack<Integer>();
        exp.chars().forEach(c -> {
            if(Character.isDigit(c)){
                sta.push(c - '0');
            } else{
                int v1 = sta.pop();
                int v2 = sta.pop();
                sta.push(performAction(v1, v2, c));
            }
        });
        return sta.pop();
    }

    public static int performAction(int v1, int v2, int c) {
        return switch (c) {
            case '+' -> v1 + v2;
            case '*' -> v1 * v2;
            case '-' -> v1 - v2;
            case '/' -> v1 / v2;
            default -> 0;
        };
    }
}
