class Solution {
	int count;
	public void robot(int idx, int[] nums, int S){
		if(idx == nums.length){
			if(S == 0)count ++;
			return;
		}
		//+
		robot(idx + 1,nums,S + nums[idx]);
		//-
		robot(idx + 1,nums,S - nums[idx]);
	}
    public int findTargetSumWays(int[] nums, int S) {
        robot(0,nums,S);
		return count;
    }
}
