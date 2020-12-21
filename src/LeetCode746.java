public class LeetCode746 {
    /**
     * 使用动态规划进行解决
     * 状态转移方程为
     * dp[i] = min(dp[i - 1] + cost[i - 1],dp[i - 2] + cost[i - 2])
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];
        for (int i = 2; i <= len; i++) {
            dp[i] = Integer.min(dp[i - 1] + cost[i - 1],dp[i - 2] + cost[i - 2]);
        }
        return dp[len];
    }
}
