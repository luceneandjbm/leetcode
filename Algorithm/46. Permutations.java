class Solution {
    List<List<Integer>> ans = new ArrayList<>();
	int[] path;//存数据
	boolean[] status;//记录状态，status[i]==true表示nums[i]已经被取了
	//在idx个位置取说明数
	public void robot(int idx, int[] nums){
		if(idx >= nums.length){
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < path.length; i ++){
				lst.add(path[i]);
			}
			ans.add(lst);
			return;
		}
		for(int i = 0; i < nums.length; i ++){
			//i位置元素没有被取
			if(!status[i]){
				path[idx] = nums[i];//idx位置取nums[i],那么其他位置就不允许取了
				status[i] = true;//nums[i]已经被取了
				robot(idx + 1, nums);
				status[i] = false;
			}
		}
	}
    public List<List<Integer>> permute(int[] nums) {
		path = new int[nums.length];
		status = new boolean[nums.length];
        robot(0,nums);
        return ans;
    }
}
