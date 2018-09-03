package Chapter1_StackAndQueue;

import java.util.Stack;

/**
 * @program: ColdingInterviewGuide
 * @description: 用一个栈实现另一个栈的排序
 * @author: Ya
 * @create: 2018-08-27 23:42
 **/
public class Staticmethod5 {
    public static void SortStakByStack(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()){
            int cur = stack.pop();

            while (!help.isEmpty() && cur > help.peek()){
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }


}
