class Solution {
	Set<List<Integer>> ans = new HashSet<>();
	int[] path = new int[150];
	int len = 0;
	public void robot(int idx, int[] nums) {
		if(len >= 2) {
            List<Integer> lst = new ArrayList<>();
            for(int j = 0; j < len; j ++) {
                lst.add(path[j]);
            }
            ans.add(lst);
        }
		for(int i = idx; i < nums.length; i ++) {
			if(len == 0 || (len > 0 && nums[i] >= path[len-1])) {
				path[len ++] = nums[i];//取
				robot(i + 1, nums);
				len --;//不取
			}
		}
	}
    public List<List<Integer>> findSubsequences(int[] nums) {
        robot(0,nums);
		return new ArrayList<>(ans);
    }
}
