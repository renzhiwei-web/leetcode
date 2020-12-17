public class LeetCode714 {
    /**
     * 使用动态规划的方法
     * 状态转移方程 dp[i][0]=max(dp[i-1][0],dp[i-1][1]+price[i]-fee)
     *            dp[i][1]=max(dp[i-1][1],dp[i-1][0]-price[i])
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0; // 第一天不买入
        dp[0][1] = -prices[0]; // 第一天买入
        for (int i = 1; i < len; i++) {
            dp[i][0] = Integer.max(dp[i - 1][0],dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Integer.max(dp[i - 1][1],dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }
}
