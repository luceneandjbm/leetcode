class Solution {
    int[] dp;
    public int robot(int n){
		if(n == 0) return 1;//刚好到平地
		if(n < 0) return 0;//走多了
		if(dp[n]>-1)return dp[n];
		
		dp[n] = robot(n-1) + robot(n-2);//走一步和走两步情况和
		return dp[n];
	}
    public int climbStairs(int n) {
        dp = new int[n+1];
		Arrays.fill(dp,-1);
		return robot(n);
    }
}
