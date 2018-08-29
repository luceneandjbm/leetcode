class Solution {
    //dp[i][j]表示取0,1,...,i的硬币（随意取），组成j金额的最少金币数
    //那么dp[i][j]的值应该为不取coins[i]的时候组成的j金额的最小值dp[i-1][j]与
    //取coins[i](dp[i][j-coins[i]] + 1),两者取较小
    public int coinChange(int[] coins, int amount) {
		int n = coins.length;
		int max = Integer.MAX_VALUE;
		int[][] dp = new int[n][amount + 1];
		//只有用coins[0]硬币的最少硬币数，而dp[i][0]显然都是0了,所有硬币都不取
		for(int j = 1; j < dp[0].length; j ++){
			if(j % coins[0] == 0){
				dp[0][j] = j / coins[0];
			}else dp[0][j] = max;
		}
		for(int i = 1; i < n; i ++){
			for(int j = 1; j < dp[0].length; j ++){
				dp[i][j] = max;
				//取coins[i]的情况
				if(coins[i]<=j && dp[i][j-coins[i]] != max){
					//既然可以取多个coins[i]那么为什么是j-coins[i]呢？
					//是因为这dp[i][j-coins[i]]也已经计算过了选i硬币的情况
					dp[i][j] = 1 + dp[i][j-coins[i]];
				}
				//不取和取之间取较大值
				dp[i][j] = Math.min(dp[i-1][j],dp[i][j]);
			}
		}
		return dp[n-1][amount] == max ? -1: dp[n-1][amount];
	}
}
