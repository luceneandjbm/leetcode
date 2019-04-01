class Solution {
    public int findMin(int[] nums) {
        int lo = 0;
		int hi = nums.length - 1;
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(nums[mid] > nums[hi]) {
				lo = mid + 1;
			}else if(nums[mid] < nums[hi]) {
				hi = mid;
			}else {
				//此时nums[mid] == nums[hi]，但是不能确定最小值在mid的左边还是右边
				//所以hi --,从而缩小了范围
				hi --;
			}
		}
		return nums[lo];
    }
}
