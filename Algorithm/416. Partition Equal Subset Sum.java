//dp[i][j]表示前i个数的和能否组成j
//dp[i][0]=true,dp[0][j] = false;
//dp[i][j] = dp[i-1][j-nums[i]] 取i的时候
//dp[i][j] = dp[i-1][j] 不取i的时候

class Solution {
	//dp[i][j]表示前i个数能否组成和为j
    public boolean canPartition(int[] nums) {
		int sum = 0;
		for(int num: nums){
			sum += num;
		}
		if(sum % 2!=0) return false;
		boolean[][] dp = new boolean[nums.length+1][sum/2 + 1];
		for(int i = 0; i < dp.length; i ++){
			dp[i][0] = true;//组成的和为0 
		}
		for(int j = 1; j < dp[0].length; j ++){
			dp[0][j] = false;//前0个数组成j
		}
		for(int i = 1; i < dp.length; i ++){
			for(int j = 1; j < dp[0].length; j ++){
				dp[i][j] = (j - nums[i-1]>=0?dp[i-1][j-nums[i-1]]:false)//取第i个数
						|| dp[i-1][j];//不取第i个数
			}
		}
		return dp[nums.length][sum/2];
    }
}
