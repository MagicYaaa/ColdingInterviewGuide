package Chapter8_ArrayAnd;

import javax.xml.stream.events.DTD;
import java.security.PublicKey;
import java.util.HashMap;

/**
 * @program: ColdingInterviewGuide
 * @description: 数组和矩阵问题
 * @author: Ya
 * @create: 2018-08-30 18:14
 **/
public class ArraySolution {
    /*转圈打印矩阵*/
    public void spiralOrderPrint(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        if (tR == dR) {//子矩阵只有一行
            for (int i = tC; i <= dC; i++) {
                System.out.print(m[tR][i] + " ");
            }
        } else if (tC == dC) {//子矩阵只有一列
            for (int i = tR; i <= dR; i++) {
                System.out.print(m[i][tC] + " ");
            }
        } else {
            int curC = tC;
            int curR = tR;
            while (curC != dC) {
                System.out.print(m[tR][curC] + " ");
                curC++;
            }
            while (curR != dR) {
                System.out.print(m[curR][dC] + " ");
                curR++;
            }
            while (curC != tC) {
                System.out.print(m[dR][curC] + " ");
                curC--;
            }
            while (curR != tR) {
                System.out.print(m[curR][tC] + " ");
                curR--;
            }
        }

    }

    /*正方形矩阵顺时针转动90度*/
    public void rotate(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length;
        int dC = matrix[0].length;
        while (tR < dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    public void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
        int times = dC - tC;//times就是总的组数
        int tmp = 0;
        for (int i = 0; i != times; i++) {
            tmp = m[tR][tC + 1];
            m[tR][tC + i] = m[dR - i][tC];
            m[dR - i][tC] = m[dR][dC - i];
            m[dR][dC - i] = m[tR + i][dC];
            m[tR + i][dC] = tmp;
        }
    }
    //2. 也可以先对角线反转 再左右对称反转

    /* 之字形打印矩阵*/

    /*找到无序数组中最小的k 个数*/
    // 堆排序

    /*需要排序的最短子数组长度*/
    public int getMinLength(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

    /*在数组中找到出现次数大于N／K的数 */
    //????
    public void printHalfMajor(int[] arr){
        int cand = 0;
        int times = 0;
        for(int i = 0; i != arr.length ; i++){
            if (times == 0) {
                cand = arr[i];
                times = 1;
            }else if(arr[i] == cand){
                times++;
            }else {
                times--;
            }
        }
        times = 0;
        for(int i = 0; i != arr.length;i++){
            if(arr[i] == cand){
                times++;
            }
        }
        if (times > arr.length / 2) {
            System.out.println(cand);
        }else {
            System.out.println("no such number");
        }
    }


    /*为排序正数数组中累加和为给定值的最长子数组长度*/
    //自己写的
    public int getMaxLength1(int[] arr, int k) {
        int left = 0;
        int right = 0;
        int max = 0;
        while (right != arr.length - 1 && left != arr.length - 1) {
            int total = 0;
            for (int i = left; i <= right; i++) {
                total += arr[i];
            }
            if (total == k) {
                max = right - left + 1 > max ? right - left + 1 : max;
            } else if (total < k) {
                right++;
            } else {
                left++;
            }
        }
        return max;
    }

    //2.书上的
    public int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0, right = 0, sum = arr[0], len = 0;
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left--];
            }
        }
        return len;
    }

    /*为排序数组中累加和为给定值的最长子数组系列问题*/
    public int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                len = Math.max(i - map.get(sum - k), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

}
