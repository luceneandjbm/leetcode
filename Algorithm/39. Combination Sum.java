class Solution {//当长度不确定的时候用len
	List<List<Integer>> ans = new ArrayList<>();
	int[] path;
	int len;
	public void robot(int idx, int[] nums, int target){
		if(target == 0){
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < len; i ++){
				lst.add(path[i]);
			}
			ans.add(lst);
			return;
		}
		if(target < 0 || idx >= nums.length) return;
		//取nums[idx]
		path[len] = nums[idx];
		len ++;
		robot(idx, nums, target-nums[idx]);
		//不取
		len --;
		robot(idx + 1, nums, target);
	}
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        path = new int[target+1];
		robot(0,candidates,target);
		return ans;
    }
}
