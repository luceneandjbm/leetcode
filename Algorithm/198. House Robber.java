class Solution {
	int[] cache;
    public int robot(int idx, int[] nums){
		if(idx < 0) return 0;
		if(cache[idx] > -1) return cache[idx];
		return cache[idx] = Math.max(nums[idx]+robot(idx - 2,nums),robot(idx -1,nums));
	}
    public int rob(int[] nums) {
        if(nums.length == 0)return 0;
		cache = new int[nums.length];
		Arrays.fill(cache,-1);
		return robot(nums.length-1, nums);
    }
}
