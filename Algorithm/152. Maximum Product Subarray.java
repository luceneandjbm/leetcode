class Solution {//注意：是连续子序列（=连续子数组）
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
		int max = nums[0];
		int min = nums[0];
        int ans = nums[0];
		for(int i = 1; i < nums.length; i ++){
			if(nums[i] < 0){
                int a = max;
                max = min;
                min = a;
			}
			max = Math.max(max*nums[i],nums[i]);
			min = Math.min(min*nums[i],nums[i]);
            ans = Math.max(ans,max);
		}
		return ans;
    }
}
