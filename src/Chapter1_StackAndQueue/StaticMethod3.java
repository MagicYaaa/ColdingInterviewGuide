package Chapter1_StackAndQueue;

import java.util.Stack;

/**
 * @program: ColdingInterviewGuide
 * @description: 如何仅用递归函数和栈操作逆序一个栈
 * @author: Ya
 * @create: 2018-08-27 21:32
 **/
public class StaticMethod3 {
    public static int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.empty()){
            return result;
        }else{
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.empty()){
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

}
