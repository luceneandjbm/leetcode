class Solution {
    int[][] cache;
    public int rob(int st, int ed, int[] nums){
		if(st > ed) return 0;
		if(cache[st][ed] != -1) return cache[st][ed];
		//偷
		int a = rob(st,ed - 2,nums) + nums[ed];
		//不偷
		int b = rob(st,ed - 1,nums);
		return cache[st][ed] = Math.max(a,b);
	}
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
		if(nums.length == 1) return nums[0];
        cache = new int[2][nums.length];
        for(int[] c : cache){
            Arrays.fill(c,-1);
        }
		return Math.max(rob(0,nums.length - 2,nums),rob(1,nums.length - 1,nums));
    }
}	 
