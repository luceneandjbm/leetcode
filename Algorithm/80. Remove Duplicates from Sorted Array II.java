class Solution {
    public int removeDuplicates(int[] nums) {
        int idx = 0;
		for(int x : nums) {
			if(idx < 2 || x > nums[idx-2]) {
				nums[idx ++] = x;
			}
		}
		return idx;
    }
}
