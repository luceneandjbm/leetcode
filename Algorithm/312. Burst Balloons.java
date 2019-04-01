class Solution {
    //dp[i][j]表示把i-1和j+1之间的气球全部打掉的最大得分,最终结果dp[0][n-1]
    //状态转移方程dp[i][j]=max(dp[i][k-1]+dp[k+1][j] + nums[i - 1]*nums[k]*nums[j+1])
    //k表示在i,j之间最后一个被打掉的气球
    public int maxCoins(int[] nums) {
        if(nums.length == 0) return 0;
		int[][] dp = new int[nums.length][nums.length];
		for(int len = 0; len <= nums.length - 1; len ++){
			for(int i = 0; i + len <= nums.length - 1; i ++){
				int j = i + len;
				for(int k = i; k <= j; k ++){
				dp[i][j] = Math.max(dp[i][j], (k-1>=i?dp[i][k-1]:0) +(k+1<=j?dp[k+1][j]:0) 
					+ (i-1>=0?nums[i-1]:1)*nums[k]*(j+1<nums.length?nums[j+1]:1));
				}
			}
		}
		return dp[0][nums.length - 1];
    }
}	 

