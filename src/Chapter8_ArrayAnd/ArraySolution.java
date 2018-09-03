package Chapter8_ArrayAnd;

import java.security.PublicKey;
import java.util.HashMap;

/**
 * @program: ColdingInterviewGuide
 * @description: 数组和矩阵问题
 * @author: Ya
 * @create: 2018-08-30 18:14
 **/
public class ArraySolution {
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
