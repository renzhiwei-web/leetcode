import org.junit.Test;

import java.util.Arrays;

public class LeetCode209 {

    /**
     * 暴力求解法
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0;i < nums.length;i++){
            int n = nums[i];
            if (n >= s){
                return min;
            }
            for (int j = i + 1;j < nums.length;j++){
                n += nums[j];
                if (n >= s){
                    min = Integer.min(j - i + 1,min);
                }
            }
        }
        return min;
    }

    /**
     * 因为数组中都是正数，保证了前缀和的递增性
     * 由于是nlogn复杂度，所以用二分查找
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen1(int s, int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        int[] sums = new int[len + 1];
        //sums[0] = 0 意味着前0个元素的前缀和为0
        //sums[1] = A[0] 前1个元素的前缀和为A[0]
        // {sums}[i]表示从{nums}[0]到nums[i−1] 的元素和
        for (int i = 1;i <= len;i++){
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1;i <= len;i++){
            int target = s + sums[i -1];
            int bound = Arrays.binarySearch(sums,target);//二分查找大于等于某个数的第一个位置的功能
            if (bound < 0){
                bound = -bound - 1;
            }
            if (bound <= len){
                min = Math.min(min,bound - (i - 1));
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     *  使用队列维护一个滑动窗口
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int lo = 0; //左边界
        int hi = 0; //右边界
        int sum = 0; //队列中
        int min = Integer.MAX_VALUE;
        while (hi < nums.length){
            sum += nums[hi++];
            while (sum >= s){
                min = Integer.min(min,hi - lo);
                sum -= nums[lo++]; //窗口左边界向右移动
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    @Test
    public void test(){
        int s = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));
    }
}
