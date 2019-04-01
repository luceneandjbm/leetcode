class Solution {
    //[7,1,5,3,6,4]
    public int maxProfit(int[] prices) {
		if(prices.length == 0) return 0;
        int ans = 0;
		for(int i = 0; i < prices.length; i ++){
			if(i>0&&prices[i]-prices[i-1]>0)
				ans += prices[i] - prices[i-1];
		}
        return ans;
    }
}
