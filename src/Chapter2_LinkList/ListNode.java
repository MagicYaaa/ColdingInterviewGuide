package Chapter2_LinkList;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.WhileLoopTree;
import jdk.nashorn.internal.ir.ReturnNode;
import org.w3c.dom.ls.LSException;

import java.awt.event.MouseWheelListener;
import java.security.interfaces.DSAPublicKey;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.logging.Level;

/**
 * @program: ColdingInterviewGuide
 * @description: 链表节点
 * @author: Ya
 * @create: 2018-08-28 22:13
 **/
public class ListNode {

    /*打印两个有序链表的公共部分*/
    public void printCommonPart(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }

    /*在单链表和双链表中删除倒数第K个节点*/
    public void deleteSingleListNode(Node head, int k) { //双指针
        Node fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        Node slow = head;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
    }

    //书上的方法
    public Node removeLastKthNode(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }
        Node cur = head;
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }
        if (lastKth == 0) {
            head = head.next;
        }
        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }

    //双链表
    //自己写的，，，有问题—。—
    public DoubleNode deleteDoubleListNode(DoubleNode head, DoubleNode rail, int k) {
        if (head == null || k < 1) {
            return head;
        }
        DoubleNode cur = rail;
        while (k > 1) {
            cur = cur.last;
            k--;
            if (cur == head && k > 0) {
                return head;
            }
        }
        if (cur == rail) {
            rail = rail.last;
        }
        if (cur == head) {
            head = head.next;
        }
        cur.last.next = cur.next;
        cur.next.last = cur.last;
        return head;
    }

    /*   删除链表的中间节点和a/b处的节点   */
    public Node removeMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /*反转链表和双链表*/
    public Node reverrseList(Node head) {
        Node p = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = p;
            p = head;
            head = next;
        }
        return p;
    }

    /*反转双链表*/
    public DoubleNode reverseDoubleList(DoubleNode head, DoubleNode rail) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    /*反转部分单向链表*/
    public Node reversePart(Node head, int from, int to) {
        Node node1 = head;
        Node fPre = null;
        Node tPos = null;
        int tag = 0;
        while (node1 != null) {
            tag++;
            fPre = tag == from - 1 ? node1 : fPre;
            tPos = tag == to + 1 ? node1 : tPos;
            node1 = node1.next;
        }

        if (from > to || from < 1 || to > tag) {
            return head;
        }

        node1 = fPre == null ? head : fPre.next;
        Node node2 = node1.next;
        node1.next = tPos;
        Node next = null;
        while (node2.next != tPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        if (fPre != null) {
            fPre.next = node1;
            return head;
        }
        return node1;
    }

    /*环形单链表的约瑟夫问题*/
    //自己写的  ，带头节点
    public Node josephusKill1(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }
        Node cur = head.next;
        while (head.next != head) {
            int i = 1;
            Node pre = head;
            Node next = cur.next;
            while (i < m) {
                cur = cur.next;
                pre = cur;
                cur = cur.next;
                i++;
            }
            if (cur == head.next) {
                head.next = cur.next;
            }
            pre.next = next;
            cur = next;
        }
        return head;
    }

    //书上的 不带头节点
    public Node josephusKill11(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    /* 用递归*/
    public Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1;
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m);
        while (--tmp != 0) {
            head = head.next;
        }
        head = head.next;
        return head;
    }

    public int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

    /*判断链表是否回文*/
    //1.利用栈
    public boolean sPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        //进行对比
        while (stack.isEmpty()) {
            if (stack.pop().value != head.value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    //2.优化 O(n) 压入一半，然后对比
    public boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next;
        Node cur = head;
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
    //3.不需要栈  ，需要修改链表结构

    /*将但链表按某值划分成左边小，中间相等，右边大的形式*/
    //分三个链表 最后合并 TODO
    public Node listPartition1(Node head, int pivot) {
        return head;
    }

    /*复制含有随机指针节点的链表*/
    //使用HashMap
    public SpecialNode copyListWithRand1(SpecialNode head) {
        HashMap<SpecialNode, SpecialNode> map = new HashMap<>();
        SpecialNode cur = head;
        while (cur != null) {
            map.put(cur, new SpecialNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    // 原地复制链表  1->1'->2->2'
    public SpecialNode copyListWithRand2(SpecialNode head) {
        if (head == null) {
            return null;
        }
        SpecialNode cur = head;
        SpecialNode next = null;
        //复制并连接每一个节点
        while (cur != null) {
            next = cur.next;
            cur.next = new SpecialNode(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        SpecialNode curCopy = null;
        //复制rand指针
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        SpecialNode res = head.next;
        cur = head;
        //拆分
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    /*两个链表生成相加链表*/
    //利用栈结构求解
    public Node addLists1(Node head1, Node head2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (head1 != null) {
            s1.push(head1.value);
            head1 = head2.next;
        }
        while (head2 != null) {
            s2.push(head2.value);
            head2 = head2.next;
        }
        int n1 = 0, n2 = 0, ca = 0, n = 0;
        Node node = null;
        Node pre = null;
        //尾插法
        while (!s1.isEmpty() || !s2.isEmpty()) {
            n1 = s1.isEmpty() ? 0 : s1.pop();
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return pre;
    }

    //先逆序求解 再逆过来
    public Node addLists2(Node head1, Node head2) {
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        int ca = 0;
        int n1 = 0, n2 = 0, n = 0;
        Node c1 = head1;
        Node c2 = head2;
        Node node = null;
        Node pre = null;
        //尾插
        while (c1 != null || c2 != null) {
            n1 = c1 == null ? 0 : c1.value;
            n2 = c2 == null ? 0 : c2.value;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        //再次逆转回去
        reverseList(head1);
        reverseList(head2);
        return node;

    }

    public Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    /*两个单链表相交的一系列问题*/
    //1.判断有无环  快慢指针法  TODO 为啥？？
    public Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next;
        Node n2 = head.next.next;
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }

        n2 = head;
        while (n1 != n2) {
            n1 = n2.next;
            n2 = n2.next;
        }
        return n1;
    }

    //2.判断 无环是否相交
    public Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) { //若想等说明有交点
            return null; //说明不相交  ， 没有交点
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //3.判断两个有环链表是否相交
    public Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;

        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 != loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    /*将单链表的每K个节点之间逆序*/
    public Node reversekNode2(Node head, int K) {
        if (K < 2) {
            return head;
        }
        Node cur = head;
        Node start = null;
        Node pre = null;
        Node next = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == K) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    public void resign2(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }

    /*删除无序链表中值重复出现的点*/
    //1.时间复杂度 O(N)
    public void removeRep1(Node head) {
        if (head == null) {
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        Node pre = head;
        Node cur = head.next;
        set.add(head.value);
        while (cur != null) {
            if (set.contains(cur.value)) {
                pre.next = cur.next;
            } else {
                set.add(cur.value);
                pre = cur;
            }
            cur = cur.next;
        }
    }

    //2.空间复杂度O(1)   类似选择排序
    public void removeRep2(Node head) {
        Node cur = head;
        Node pre = null;
        Node next = null;

        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
                if (cur.value == next.value) {
                    pre.next = next.next;
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }
    }

    /*再单链表中删除指定值的节点*/
    //1.利用栈（容器）收集链表中符合条件的节点，然后吐出来 生成新的链表
    public Node removeValue1(Node head, int num) {
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            if (head.value != num) {
                stack.push(head);
            }
        }
        //此时head == null
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }

    //2.直接遍历删除,注意第一个值
    public Node removeValue2(Node head, int num) {
        while (head.value == num) {
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /*将搜索二叉树转换成双向链表*/
    //1.利用队列容器 收集中序遍历结果
    public BinaryTreeNode convert1(BinaryTreeNode head) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        inOrderToQueue(head, queue);
        head = queue.poll();
        BinaryTreeNode pre = head;
        BinaryTreeNode left = null;
        BinaryTreeNode cur = null; //???
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    //  中序遍历 加入队列
    public void inOrderToQueue(BinaryTreeNode head, Queue<BinaryTreeNode> queue) {
        if (head == null) {
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }

    //2.递归方法
    public BinaryTreeNode convert2(BinaryTreeNode head) {
        if (head == null) {
            return null;
        }
        BinaryTreeNode last = process(head);
        head = last.right;
        last.right = null;
        return head;
    }

    //递归函数
    public BinaryTreeNode process(BinaryTreeNode head) {
        if (head == null) {
            return null;
        }
        BinaryTreeNode leftEnd = process(head.left);
        BinaryTreeNode rightEnd = process(head.right);
        BinaryTreeNode leftS = leftEnd != null ? leftEnd.right : null;
        BinaryTreeNode rightS = rightEnd != null ? rightEnd.right : null;
        if (leftEnd != null && rightEnd != null) {
            leftEnd.right = head;
            head.left = leftEnd;
            head.right = rightS;
            rightS.left = head;
            rightEnd = leftS;
            return rightEnd;
        } else if (leftEnd != null) {
            leftEnd.right = head;
            head.left = leftEnd;
            head.right = leftS;
            return head;
        } else if (rightEnd != null) {
            head.right = rightS;
            rightS.left = head;
            rightEnd.right = head;
            return rightEnd;
        } else {
            head.right = head;
            return head;
        }
    }


    /*单链表的选择排序*/
    public static Node selectionSort(Node head) {
        Node tail = null; //排序部分尾部
        Node cur = head;  //为排序部分头部
        Node minPre = null; //最小节点的前一个节点
        Node min = null;  //最小节点
        while (cur != null) {
            min = cur;
            minPre = getSmallestPreNode(cur);
            if (min != null) {
                min = minPre.next;
                minPre.next = min.next;
            }
            cur = cur == min ? cur.next : cur;
            if (tail == null) {

                head = min;
            } else {
                tail.next = min;
            }
            tail = min;
        }
        return head;
    }

    //寻找链表最小节点
    public static Node getSmallestPreNode(Node head) {
        Node pre = head;
        Node cur = head.next;
        Node next = null;
        Node minPre = null;
        Node min = head;
        while (cur != null) {
            if (cur.value > min.value) {
                min = cur;
                minPre = pre;
            }
            pre = cur;
            cur = cur.next;
        }
        return minPre;
    }

    /*一种怪异的节点删除方式*/
    public void removeNodeWired(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        if (next == null) {
            throw new RuntimeException("不能删除最后一个节点");
        }
        node.value = next.value;
        node.next = next.next;
    }

    /*向有序的环形单链表中插入新节点*/
    public Node insertNum(Node head, int num) {
        Node node = new Node(num);
        //如果单链表为空
        if (head == null) {
            node.next = node;
            return node;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head) {
            if (num >= pre.value && num <= cur.value) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return head.value < num ? head : node;
    }

    /*合并两个有序的单链表*/
    public Node merge(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        Node head = head1.value <= head2.value ? head1 : head2;
        Node cur1 = head1;
        Node cur2 = head2;
        Node pre = null;
        Node next = null;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = cur2.next;
            }

        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

    /*按照左右半区的方式重新组合单链表*/
    public void relocate(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = head;
        Node right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);
    }

    //合并
    public void mergeLR(Node left, Node right) {
        Node next = null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left = right.next;
            right = next;
        }
        left.next = right;
    }
}


class Node {
    public int value;
    public Node next;

    public Node(int data) {
        this.value = data;
    }
}

class DoubleNode {
    public int value;
    public DoubleNode last;
    public DoubleNode next;

    public DoubleNode(int data) {
        this.value = data;
    }
}

class SpecialNode {
    public int value;
    public SpecialNode next;
    public SpecialNode rand;

    public SpecialNode(int data) {
        this.value = data;
    }
}

class BinaryTreeNode {
    public int value;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode(int data) {
        this.value = data;
    }
}
