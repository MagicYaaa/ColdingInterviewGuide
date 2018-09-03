package Chapter5_String;

/**
 * @program: ColdingInterviewGuide
 * @description: 字符串问题
 * @author: Ya
 * @create: 2018-09-03 20:48
 **/
public class StringSolution {
    /*判断两个字符串是否互为变形词*/
    public boolean isDeformation(String str1, String str2) {
        if(str1 == null || str2 == null || str1.length() != str2.length()){
            return false;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[] map = new int[256]; //词频统计表
        for(int i = 0; i < str1.length(); i++){
            map[chs1[i]]++;
        }
        for(int i = 0; i < str2.length();i++){
            if(map[chs2[i]]-- == 0){
                return false;
            }
        }
        return true;
    }

    /*字符串中数字子串的求和*/



    /*去掉字符串中连续出现k个0的子串*/

    /*判断两个字符串是否为旋转词*/

    /**/


}
