package Chapter1_StackAndQueue;

import java.sql.Statement;
import java.util.Map;
import java.util.Stack;

/**
 * @program: ColdingInterviewGuide
 * @description: 求最大矩阵大小
 * @author: Ya
 * @create: 2018-08-28 15:51
 **/
public class MaxRecSize {
    /*public int findMaxRexSize(int[][] map){
        int max = 0;
        for(int row = 0; row < map.length; row++){


            for(int column = 0; column < map[0].length; column++){

            }
        }
    }*/
    public int maxRecSize(int[][] map){
        if(map ==null || map.length == 0 || map[0].length==0){
            return 0;
        }
        int maxArea = 0;
        int [] height = new int[map[0].length];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length;j++){
                height[j] = map[i][j] == 0 ? 0: height[j]+1;
            }
            maxArea = Math.max(maxRecFromBotton(height),maxArea);
        }

        return maxArea;
    }
    public int maxRecFromBotton(int[] height){
        if(height == null || height.length == 0){
            return  0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i =0; i < height.length; i++){
            while (!stack.isEmpty() &&height[i] <= height[stack.peek()]){
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - j) * height[j];
                maxArea = Math.max(maxArea,curArea);
            }
            stack.push(i) ;
        }
        while (!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty() ? -1: stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea,curArea);

        }
        return maxArea;
    }
}
