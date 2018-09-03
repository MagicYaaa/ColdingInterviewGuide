package Chapter1_StackAndQueue;

import java.util.Stack;

/**
 * @program: ColdingInterviewGuide
 * @description: 构造数组的MaxTree
 * @author: Ya
 * @create: 2018-08-28 13:26
 **/
public class MaxTree {

    public Node getMaxTree(int[] arr){
        Node[] nArr = new Node[arr.length];
        for(int i = 0; i != arr.length;i++){
            nArr[i] = new Node(arr[i]);
        }
        Stack<Node> stack = new Stack<>();




        return null;
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
        this.value = data;
    }
}
