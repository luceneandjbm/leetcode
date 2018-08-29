class Solution {
    
	//dp[i]表示组成i的时候最小的组成个数,注意dp[0]不是1而是0
    //n 可以由k*k +(n-k*k)转换过来，所以dp[n]=min(dp[n],1+dp[n-k*k])
    public int numSquares(int n) {
		if(n == 0)return 0;
		int[] dp = new int[n + 1];
        Arrays.fill(dp,1000000);
        dp[0] = 0;
		for(int i = 1; i < n + 1; i ++){
			for(int j = 1; j * j <= i; j ++){
				dp[i] = Math.min(dp[i],dp[i-j*j] + 1);
			}
		}
		return dp[n];
    }
}

