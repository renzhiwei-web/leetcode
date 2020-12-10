import org.junit.Test;

import java.util.TreeSet;

public class LeetCode220 {

    /**
     * 重点了解TreeSet中的celling函数
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        for (int i = 0; i < nums.length - 1;i++){
//            for (int j = i + 1;j <= i + k && j < nums.length;j++){
//                long a = nums[i];
//                long b = nums[j];
//                long x = Math.abs(a - b);
//                if (x <= t){
//                    return true;
//                }
//            }
//        }
//        return false;
        // 滑动窗口结合查找表，此时滑动窗口即为查找表本身（控制查找表的大小即可控制窗口大小）
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 边添加边查找
            // 查找表中是否有大于等于 nums[i] - t 且小于等于 nums[i] + t 的值
            // celling方法返回大于等于 key 的最小元素，如果不存在，返回空
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= ((long) nums[i] + (long) t)) {
                return true;
            }
            // 添加后，控制查找表（窗口）大小，移除窗口最左边元素
            set.add((long) nums[i]);
            if (set.size() == k + 1) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    @Test
    public void test(){
        int[] nums = {-2147483648,2147483647};
        System.out.println(containsNearbyAlmostDuplicate(nums, 1, 1));
    }
}
