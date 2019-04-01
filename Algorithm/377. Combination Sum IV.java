class Solution {
    int[] cache;
	public int robot(int[] nums, int target){
		if(target == 0) return 1;
		if(cache[target] != -1) return cache[target];
		int ans = 0;
		for(int i = 0; i < nums.length; i ++){
			if(target  - nums[i] >= 0){
				ans += robot(nums, target - nums[i]);
			}
		}
		return cache[target] = ans;
	}
    public int combinationSum4(int[] nums, int target) {
        cache = new int[target + 1];
		Arrays.fill(cache,-1);
		return robot(nums,target);
    }
}

使用dp
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
		dp[0] = 1;
		for(int i = 1; i < target + 1; i ++){
			for(int j = 0; j < nums.length; j ++){
				if(i - nums[j] >= 0){
					dp[i] += dp[i-nums[j]];
				}
			}
		}
		return dp[target];
    }
}
