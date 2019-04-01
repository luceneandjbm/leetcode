class Solution {
    int[] dp;
	public int robot(int idx, int[] cost){
		if(idx >= cost.length) return 0;
		if(dp[idx] > -1) return dp[idx];
		dp[idx] = Math.min(cost[idx] + robot(idx+1,cost),cost[idx] + robot(idx+2,cost));
		return dp[idx];
	}
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length == 0) return 0;
		dp = new int[cost.length];
		Arrays.fill(dp,-1);
		return Math.min(robot(0,cost),robot(1,cost));
    }
}
