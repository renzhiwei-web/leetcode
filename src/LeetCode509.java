public class LeetCode509 {
    /**
     * 两种方法求斐波那契数列：递归和动态规划
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
//        return helper(n);
    }

    private int helper(int n){
        if (n == 1){
            return 1;
        } else if (n == 0){
            return 0;
        } else {
            return helper(n - 1) + helper(n - 2);
        }
    }
}
