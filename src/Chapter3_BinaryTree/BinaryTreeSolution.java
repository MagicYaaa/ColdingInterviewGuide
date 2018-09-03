package Chapter3_BinaryTree;

import jdk.nashorn.internal.ir.ReturnNode;

import java.util.*;

/**
 * @program: ColdingInterviewGuide
 * @description: 二叉树问题
 * @author: Ya
 * @create: 2018-08-30 14:08
 **/
public class BinaryTreeSolution {
    /*分别用递归和非递归方式实现二叉树的先中后序遍历*/
    //先序遍历 递归实现
    public void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");//访问根
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    //中序遍历 递归实现
    public void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");//访问根
        preOrderRecur(head.right);
    }

    //后序遍历 递归实现
    public void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        inOrderRecur(head.right);
        System.out.print(head.value + " ");//访问根

    }

    //先序遍历 非递归实现
    public void preOrderUnRecur(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");//访问根
            if (head.right != null) {
                stack.push(head.right);
            }
            if (head.left != null) {
                stack.push(head.left);
            }
        }
    }

    //中序遍历 非递归实现
    public void inOrderUnRecur(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
    }

    //后序遍历 非递归实现
    //1.双栈
    public void posOrderUnRecur(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()) {
            head = s1.pop();
            s2.push(head);
            if (head.left != null) {
                s1.push(head.left);

            }
            if (head.right != null) {
                s1.push(head.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
    }

    //2.单栈 ???????????/没看懂
    public void posOrderUnRecur2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node c = null;
        while (!stack.isEmpty()) {
            c = stack.peek();
            if (c.left != null && head != c.left && head != c.right) {
                stack.push(c.left);
            } else if (c.right != null && head != c.right) {
                stack.push(c.right);
            } else {
                System.out.print(stack.pop().value + " ");
                head = c;
            }

        }
    }

    //自己写的 有点问题！！！！！！！
    public void posOrderUnRecur3(Node head) {
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node cur = null;//记录当前节点
        Node temp = null;//记录上个打印的节点
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (cur.left == temp || cur.right == temp) {
                if (cur.right != null) {
                    stack.push(cur.right);
                } else if (cur.left != null) {
                    stack.push(cur.left);
                    continue;
                }
            } else {
                temp = stack.pop();
                System.out.print(temp.value + " ");
            }
        }
    }

    /*打印二叉树的边界*/  //TODO ??
    //标准1
    public void printEdge1(Node head) {

    }

    public int getHeight(Node h, int l) {
        if (h == null) {
            return l;
        }
        return Math.max(getHeight(h.left, l + 1), getHeight(h.right, l + 1));
    }

    public void setEdgeMap(Node h, int l, Node[][] edgeMap) {
        if (h == null) {
            return;
        }
        edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
        edgeMap[l][1] = h;
        setEdgeMap(h.left, l + 1, edgeMap);
        setEdgeMap(h.right, l + 1, edgeMap);
    }


    /*如何较为直观的打印二叉树*/
    //自己写的 TODO 没写完啊 ～～～
    public void printBinaryTree(Node head) {
        int len = findDeepTree(head, 0);
        len = (int) Math.pow(2, len - 1);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        int size = queue.size();

        while (!queue.isEmpty()) {
            while (size != 0) {
                System.out.print(queue.poll().value);

                size--;
            }
            size = queue.size();
        }
    }

    //求最大深度
    public int findDeepTree(Node head, int deep) {
        if (head == null) {
            return deep;
        }
        return Math.max(findDeepTree(head.left, deep + 1), findDeepTree(head.right, deep + 1));
    }

    /*二叉树的序列化与反序列化*/
    public String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    /*反序列化*/
    public Node reconByPreString(String preStr) {
        String[] valuse = preStr.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < valuse.length; i++) {
            queue.offer(valuse[i]);
        }
        return reconPreOrder(queue);
    }

    //
    public Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }



    /*遍历二叉树的神级方法！！*/   //TODO
    //利用的线索二叉树

    /*再二叉树中找到累加和为指定值的最长路径长度*/
    public int getMaxLength(Node head, int sum) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 0);
        return preOrder(head, sum, 0, 1, 0, sumMap);
    }

    public int preOrder(Node head, int sum, int preSum, int level, int maxLen,
                        HashMap<Integer, Integer> summap) {
        if (head == null) {
            return maxLen;
        }
        int curSum = preSum + head.value;
        if (!summap.containsKey(curSum)) {
            summap.put(curSum,level);
        }
        if (summap.containsKey(curSum - sum)) {
            maxLen = Math.max(level-summap.get(curSum-sum),maxLen);
        }
        maxLen = preOrder(head.left,sum,curSum,level+1,maxLen,summap);
        maxLen = preOrder(head.right,sum,curSum,level+1,maxLen,summap);
        if (level == summap.get(curSum)) {
            summap.remove(curSum);
        }
        return maxLen;
    }

    /*找到二叉树中的最大搜索二叉子树*/
    

    //========================================
    //二叉树结构
    class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
}
