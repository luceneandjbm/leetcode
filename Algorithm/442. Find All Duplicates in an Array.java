class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
		int n = nums.length;
		for(int i = 0; i < n; i ++) {
			int idx = Math.abs(nums[i]) - 1;//转换为下标
			if(nums[idx] < 0) {//标记
				ans.add(Math.abs(nums[i]));
			}else {
				nums[idx] = - nums[idx];
			}
		}
		
		return ans;
    }
}
