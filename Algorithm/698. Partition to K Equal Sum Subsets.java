class Solution {
	boolean[] status;
	public boolean robot(int[] nums, int k, int idx, int sum,int target){
		if(sum > target) return false;
		if(sum == target) return robot(nums,k - 1,0,0,target);
		if(k == 0) return true;
		for(int i = idx; i < nums.length; i ++){
			if(!status[i]){
				status[i] = true;
				if(robot(nums,k,i+1,sum+nums[i],target)) return true;
				status[i] = false;
			}
		}
		return false;
	}
    public boolean canPartitionKSubsets(int[] nums, int k) {
		status = new boolean[nums.length];
		int sum = 0;
		for(int num : nums){
			sum += num;
		}
		if(sum%k != 0) return false;
		return robot(nums,k,0,0,sum/k);
		
    }
}
