class Solution { 
	List<List<Integer>> ans = new ArrayList<>();
	int[] path;
    boolean[] status;
	public void robot(int idx, int[] nums){
		if(idx == nums.length){
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < nums.length; i ++){
				lst.add(path[i]);
			}
			ans.add(lst);
			return;
		}
		
		for(int i = 0; i < nums.length; i ++){
			if(i > 0 && nums[i] == nums[i-1] && !status[i-1]){
				continue;
			}
			if(!status[i]){
				status[i] = true;
				path[idx] = nums[i];
				robot(idx + 1, nums);
				status[i] = false;
			}
		}
	}
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
		path = new int[nums.length];
        status = new boolean[nums.length];
		robot(0,nums);
		return ans;
    }
}
