class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
		int n = nums.length;
		for(int i = 0; i < n; i ++) {
			int idx = Math.abs(nums[i]) - 1;//转换为下标
			if(nums[idx] > 0) {//标记
				nums[idx] = - nums[idx];
			}
		}
		//如果某个位置还是正数，说明没有对应的idx，也就是没有对应的数值
		for(int i = 0; i < n; i ++) {
			//假设nums[3] = 100,说明没有下标99，说明数组中没有100这个数
			if(nums[i] > 0) ans.add(i + 1);
		}
		return ans;
    }
}
