import java.util.ArrayList;
import java.util.List;

public class LeetCode228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        int i = 0; // i初始指向第1个区间的起始位置
        for (int j = 0; j < n; j++) {
            // j向后遍历，直到不满足连续递增（即nums[j]+1!=nums[j+1]）
            // 或者j达到数组的边界
            if (j + 1 == nums.length || nums[j] + 1 != nums[j + 1]) {
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                if (i != j) {
                    sb.append("->").append(nums[j]);
                }
                res.add(sb.toString());
                // 将i指向更新为j+1，作为下个区间的起始位置
                i = j + 1;
            }
        }
        return res;
    }
}
