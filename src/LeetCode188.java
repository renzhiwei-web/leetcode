public class LeetCode188 {
    /**
     * 使用动态规划的方法，状态转移方程为
     * dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + prices[i]
     * dp[i][1] = dp[i - 1][1] + dp[i - 1][0] - prices[i]
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        //当k非常大时转为无限次交易,因为不能同时进行两笔交易，所以当k>=n/2时，就可以转换成无限次交易
        if (k >= n / 2) {
            int dp0 = 0, dp1 = -prices[0];
            for (int i = 1; i < n; ++i) {
                dp0 = Math.max(dp0, dp1 + prices[i]);
                dp1 = Math.max(dp1, dp0 - prices[i]);
            }
            return Math.max(dp0, dp1);
        }
        //定义二维数组，交易了多少次、当前的买卖状态
        int[][] dp = new int[k + 1][2];
        for (int i = 0; i <= k; ++i) {
            dp[i][0] = 0;
            dp[i][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = k; j > 0; --j) {
                //处理第k次买入
                dp[j - 1][1] = Math.max(dp[j - 1][1], dp[j - 1][0] - prices[i]);
                //处理第k次卖出
                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] + prices[i]);

            }
        }
        return dp[k][0];
    }
}
