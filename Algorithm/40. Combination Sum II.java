class Solution {
	List<List<Integer>> ans = new ArrayList<>();
	boolean[] status;//去重用的
	public void robot(int idx, int[] nums, int target){
		if(target < 0) return;
		if(target == 0&&idx <= nums.length){
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < nums.length; i ++){
                if(status[i]) lst.add(nums[i]);
			}
			ans.add(lst);
			return;
		}
		if(idx >= nums.length)return;
		if(idx >0 && nums[idx] == nums[idx-1] && !status[idx-1]){
			//只能不取
			robot(idx + 1, nums, target);
		}else {
			//取
			status[idx] = true;
			robot(idx + 1, nums, target - nums[idx]);
			//不取
			status[idx] = false;
			robot(idx + 1, nums, target);
		}
	}
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
		status = new boolean[candidates.length];
		robot(0,candidates,target);
		return ans;
    }
}
