class Solution {
    public int removeDuplicates(int[] nums) {
		int idx = 1;//0位置肯定是要保存下来的
        for(int i = 1; i < nums.length; i ++){
			if(nums[i]!=nums[i-1]){
				nums[idx++] = nums[i];
			}
		}
		return idx;
    }
}
