class Solution {
    public int maxProfit(int[] prices) {
		if(prices.length == 0) return 0;
		int n = prices.length;
		// int[] dp = new int[n];//dp[i]表示i天卖出的最大利润
		// int min = Integer.MAX_VALUE;
		// for(int i = 0; i < n; i ++){
		// 	min = Math.min(min,prices[i]);
		// 	dp[i] = prices[i] - min;
		// }
		// int ans = 0;
		// for(int d : dp){
		// 	if(ans < d) ans = d;
		// }
		// return ans;
        int ans = 0;
        int min = Integer.MAX_VALUE;
        for(int p : prices) {
            min = Math.min(min,p);
            ans = Math.max(ans,p-min);
        }
        return ans;
    }
}
