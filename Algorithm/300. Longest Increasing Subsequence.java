class Solution {
    //dp[i]表示下标i结尾的最长上升序列长度
	//这题使用二分可以将时间复杂度降低到nlogn
    public int lengthOfLIS(int[] nums) {
		if(nums.length == 0)return 0;
		int[] dp = new int[nums.length];
		Arrays.fill(dp,1);
		int max = 1;
		for(int i = 1; i < nums.length; i ++){
			for(int j = 0; j < i; j ++){
				if(nums[i] > nums[j]){
					dp[i] = Math.max(dp[i],dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		return max;
    }
}
