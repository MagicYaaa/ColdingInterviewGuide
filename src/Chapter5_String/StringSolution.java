package Chapter5_String;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import javax.naming.RefAddr;
import javax.xml.stream.FactoryConfigurationError;

/**
 * @program: ColdingInterviewGuide
 * @description: 字符串问题
 * @author: Ya
 * @create: 2018-09-03 20:48
 **/
public class StringSolution {
    /*判断两个字符串是否互为变形词*/
    public boolean isDeformation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[] map = new int[256]; //词频统计表
        for (int i = 0; i < str1.length(); i++) {
            map[chs1[i]]++;
        }
        for (int i = 0; i < str2.length(); i++) {
            if (map[chs2[i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    /*字符串中数字子串的求和*/
    public int numSum(String str) {
        if (str == null) {
            return 0;
        }
        char[] charArr = str.toCharArray();
        int res = 0;
        int num = 0;
        boolean posi = true;
        int cur = 0;
        for (int i = 0; i < charArr.length; i++) {
            cur = charArr[i] - '0';
            if (cur < 0 || cur > 9) {
                res += num;
                num = 0;
                if (charArr[i] == '-') {
                    if (i - 1 > -1 && charArr[i - 1] == '-') {
                        posi = !posi;
                    } else {
                        posi = false;
                    }
                } else {
                    posi = true;
                }
            } else {
                num = num * 10 + (posi ? cur : -cur);
            }
        }
        res += num;
        return res;
    }

    /*去掉字符串中连续出现k个0的子串*/
    public String removeKZeros(String str, int k) {
        if (str == null || k < 1) {
            return str;
        }
        char[] chas = str.toCharArray();
        int count = 0, start = -1;
        for (int i = 0; i != chas.length; i++) {
            if (chas[i] == '0') {
                count++;
                start = start == -1 ? i : start;
            } else {
                if (count == k) {
                    while (count-- != 0) {
                        chas[start++] = 0; //??? 如何去除的？？？？
                    }
                    count = 0;
                    start = -1;
                }
            }
        }
        if (count == k) {
            while (count-- != 0) {
                chas[start++] = 0;
            }
        }
        return String.valueOf(chas);
    }


    /*判断两个字符串是否为旋转词*/
    public boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        return false;
        // return getIndexOf(b2,a) != -1; //getIndexOf -> KMP Algorithm
    }


    /*将整数字符串转成整数值*/
    //先判断是否有效
    public boolean isValid(char[] chas) {
        if (chas[0] != '-' && (chas[0] < '0' || chas[0] > '9')) {
            return false;
        }
        if (chas[0] == '-' && (chas.length == 1 || chas[1] == '0')) {
            return false;
        }
        if (chas[0] == '0' && chas.length < 1) {
            return false;
        }
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] < '0' || chas[i] > '9') {
                return false;
            }
        }
        return true;
    }

    //再判定—。— 是否溢出
    public int convert(String str) {
        if (str == null || str.equals("")) {
            return 0; //不能转
        }
        char[] chas = str.toCharArray();
        if (!isValid(chas)) {
            return 0;
        }
        boolean posi = chas[0] == '-' ? false : true;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;
        int cur = 0;
        for (int i = posi ? 0 : 1; i < chas.length; i++) {
            cur = '0' - chas[i];
            if ((res < minq) || (res == minq && cur < minr)) { //溢出判定
                return 0;
            }
            res = res * 10 + cur;
        }
        if (posi && res == Integer.MIN_VALUE) {
            return 0;
        }
        return posi ? -res : res;
    }

    /*替换字符串中连续出现的指定字符串*/

    /*字符串的统计字符串*/
    //1.  aaabb -> a_3_b_2
    public String getCountString(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char cur = str.charAt(0);
        int num = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == cur) {
                num++;
            } else {
                String s = cur + "_" + num + "_";
                sb.append(s);
                cur = str.charAt(i);
                num = 1;
            }
        }
        String s = cur + "_" + num + "_";
        sb.append(s);

        sb.delete(sb.length() - 1, sb.length());
        return String.valueOf(sb);
    }


    //2.补充问题
    public char getCharAt(String cstr, int index) {
        if (cstr == null || cstr.length() == 0) {
            return 0;
        }
        int total = 0;
        int num = 0;
        boolean tag = true;
        char cur;
        for (int i = 0; i < cstr.length(); i++) {
            cur = cstr.charAt(i);
            i++;
            while (i < cstr.length() && cstr.charAt(i) != '_') {
                num = num * 10 + (cstr.charAt(i) - '0');
            }
            total += num;
            num = 0;
            if (index < total) {
                return cur;
            }
        }
        return 0;
    }

    /*判断字符数组中是否所有的字符都只出现一次*/
    //1.利用字典  char[255]

    //2.利用非递归的堆排序
    //2.1主函数
    public boolean isUnique2(char[] chas) {
        if (chas == null) {
            return true;
        }
        heapSort(chas);
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == chas[i - 1]) {
                return false;
            }
        }
        return true;
    }

    //2.2堆排序非递归实现
    public void heapSort(char[] chas) {
        for (int i = 0; i < chas.length; i++) {
            heapInsert(chas, i);
        }
        for (int i = chas.length - 1; i > 0; i--) {
            swap(chas, 0, i);
            heapify(chas, 0, i);
        }
    }

    public void heapInsert(char[] chas, int i) {
        int parent = 0;
        while (i != 0) {
            parent = (i - 1) / 2;
            if (chas[parent] < chas[i]) {
                swap(chas, parent, i);
                i = parent;
            } else {
                break;
            }
        }
    }

    public void heapify(char[] chas, int i, int size) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while (left < right) {
            if (chas[left] > chas[i]) {
                largest = right;
            }
            if (right < size && chas[right] > chas[largest]) {
                largest = size;
            }
            if (largest != i) {
                swap(chas, largest, i);
            } else {
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    public void swap(char[] chas, int index1, int index2) {
        char temp = chas[index1];
        chas[index1] = chas[index2];
        chas[index2] = temp;
    }


    /*在有序但含有空的数组中查找字符串*/
    //使用二分法
    public int getIndex(String[] strs, String str) {
        if (strs == null || strs.length == 0 || str == null) {
            return -1;
        }
        int res = -1;
        int left = 0;
        int right = strs.length - 1;
        int mid = 0;
        int i = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (strs[mid] != null && strs[mid].equals(str)) {
                res = mid;
                right = mid - 1;
            } else if (strs[mid] != null) {
                if (strs[mid].compareTo(str) < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                i = mid;
                while (strs[i] == null && --i >= left)
                    ;
                if (i < left || strs[i].compareTo(str) < 0) {
                    left = mid + 1;
                } else {
                    res = strs[i].equals(str) ? i : res;
                    right = i - 1;
                }
            }
        }
        return res;
    }

    /*字符串的调整与替换*/
    public void replace(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }
        int num = 0;
        int len = 0;
        for (len = 0; len < chas.length && chas[len] != 0; len++) {
            if (chas[len] == ' ') {
                num++;
            }
        }
        int j = len + num * 2 - 1;
        for (int i = len - 1; i > -1; i--) {
            if (chas[i] != ' ') {
                chas[j--] = chas[i];
            } else {
                chas[j--] = '0';
                chas[j--] = '2';
                chas[j--] = '%';
            }
        }
    }

    //补充问题
    public void modify(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }
        int j = chas.length - 1;
        for (int i = chas.length - 1; i > -1; i--) {
            if (chas[i] != '*') {
                chas[j--] = chas[i];
                //chas[i] = '*';
            }
        }
        //这个可以覆盖前面的
        for (; j > -1; ) {
            chas[j--] = '*';
        }
    }


    /*翻转字符串*/
    //1.先整体逆序  再单词逆序
    public void rotateWord(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }
        reverse(chas, 0, chas.length - 1);
        int l = -1;
        int r = -1;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != ' ') {
                l = i == 0 || chas[i - 1] == ' ' ? i : l;
                r = i == chas.length - 1 || chas[i + 1] == ' ' ? i : r;
            }
            if (l != -1 && r != -1) {
                reverse(chas, l, r);
                l = -1;
                r = -1;
            }
        }
    }

    //反转字符串
    public void reverse(char[] chas, int start, int end) {
        char tmp = 0;
        while (start < end) {
            tmp = chas[start];
            chas[start] = chas[end];
            chas[end] = tmp;
            start++;
            end--;
        }
    }

    //补充题目   逆序 再逆序
    public void rotate1(char[] chas, int size) {
        if (chas == null || chas.length == 0 || size >= chas.length) {
            return;
        }
        reverse(chas, 0, size - 1);
        reverse(chas, size, chas.length - 1);
        reverse(chas, 0, chas.length - 1);
    }
    //补充题目的 递归交换




    /*数组中两个字符串的最小距离*/

    /*添加最少字符使字符串整体都是会问字符串*/

    /*括号字符串的有效性和最长有效长度*/

    /*公式字符串求值*/

    /*0左边必有1的二进制字符串数量*/

    /*。。。。。。*/

    /*字符串匹配*/

    /*字典树 前缀树的实现*/

}
