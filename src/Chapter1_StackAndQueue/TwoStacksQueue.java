package Chapter1_StackAndQueue;

import java.util.Stack;

/**
 * @program: ColdingInterviewGuide
 * @description: 由两个栈组成的队列
 * @author: Ya
 * @create: 2018-08-27 21:15
 **/
public class TwoStacksQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    public TwoStacksQueue(){
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    public void add(int pushInt){
        stackPush.push(pushInt);
    }

    public int poll(){
        if(stackPop.empty() && stackPush.empty()){
            throw new RuntimeException("Queue is empty!");
        }else if(stackPop.empty()){
            while ((!stackPush.empty())){
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek(){
        if(stackPop.empty() && stackPush.empty()){
            throw new RuntimeException("Queue is empty!");
        }else if(stackPop.empty()){
            while (!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }

}
