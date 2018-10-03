class Solution {
    public int findMin(int[] nums) {
		int n = nums.length;
		if(n == 0) return -1;
        int lo = 0;
		int hi = n - 1;
		while(lo < hi) {
			int mid = lo + (hi - lo)/2;
			if(nums[mid] >= nums[lo] && nums[mid] >= nums[hi]) {
				lo = mid + 1;
			}else {
				hi = mid;
			}
		}
		return nums[lo];
    }
}
