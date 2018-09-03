package Chapter1_StackAndQueue;

import java.util.Stack;

/**
 * @program: ColdingInterviewGuide
 * @description: 设计一个有getMin功能的栈
 * @author: Ya
 * @create: 2018-08-27 19:20
 **/

public class MyStack1 {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack_min = new Stack<>();


    public void push(Integer integer){
        stack1.push(integer);
        if(stack_min.empty()){
            stack_min.push(integer);
        }else{
            if(integer <= stack_min.peek()){
                stack_min.push(integer);
            }
        }
    }

    public int pop(){
        if(stack1.peek() == stack_min.peek()){
            stack_min.pop();
        }
        return stack1.pop();
    }

    public int getMin(){
        return stack_min.peek();
    }
}
