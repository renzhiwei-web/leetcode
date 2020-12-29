import org.junit.Test;

import java.util.Arrays;

/**
 * 本文采用贪心算法的思想，每次都用最小的饼干去满足胃口孩子
 * 所以先对孩子和饼干进行排序，然后同时遍历小孩的胃口和饼干，如果已经没有饼干或者最大的饼干都满足不了正在遍历的小孩，那么就结束遍历
 */
public class LeetCode455 {
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0){ // 特判
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        if (g[0] > s[s.length - 1]){ // 如果最大的饼干都满足不了正在遍历的小孩，就直接返回0
            return 0;
        }
        int child = 0;
        int j = 0;
        for (int i = 0;i < g.length;i++){
            for (;j < s.length;j++){
                if (g[i] <= s[j]){// 如果当前饼干能够满足当前小孩，那么就直接结束循坏
                    child++;
                    j++;// break前先j++，
                    break;
                }
                if (j == s.length - 1 && g[i] > s[j]){
                    return child;
                }
            }
        }
        return child;
    }

    @Test
    public void test(){
        int[] g = {1,2,3,3,3,3,3,3,3,3,3};
        int[] s = {1,2,3,4,5,6,7};
        findContentChildren(g,s);
    }
}
