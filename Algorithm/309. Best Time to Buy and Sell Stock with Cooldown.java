class Solution {
	//s0[i]表示在0-i位置持有股票的最大利润
	//s1[i]表示在0-i位置不持有股票，而且下次可以买入的最大利润
	//s2[i]表示在0-i位置持有股票，但是下次不能买入的最大利润
    public int maxProfit(int[] prices) {
		if(prices.length == 0) return 0;
		int n = prices.length;
		
		int[] s0 = new int[n];
		int[] s1 = new int[n];
		int[] s2 = new int[n];
		s0[0] = - prices[0];
		s1[0] = 0;
		s2[0] = 0;
		for(int i = 1; i < n; i ++){
			s0[i] = Math.max(s0[i-1],s1[i-1]-prices[i]);
			s1[i] = Math.max(s1[i-1],s2[i-1]);
			s2[i] = s0[i-1] + prices[i];
		}
		return Math.max(s1[n-1],s2[n-1]);
    }
}
