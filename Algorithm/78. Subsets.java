class Solution {
	List<List<Integer>> ans = new ArrayList<>();
	boolean[] status;
    public void robot(int idx, int[] nums){
		if(idx >= nums.length){
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < nums.length; i ++){
				if(status[i])lst.add(nums[i]);
			}
			ans.add(lst);
			return;
		}
		
		//取
		status[idx] = true;
		robot(idx + 1, nums);
		//不取
		status[idx] = false;
		robot(idx + 1, nums);
	}
    public List<List<Integer>> subsets(int[] nums) {
        status = new boolean[nums.length];
		robot(0,nums);
		return ans;
    }
}
