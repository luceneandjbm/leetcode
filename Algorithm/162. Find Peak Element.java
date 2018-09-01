class Solution {//[1,2,1,3,5,6,4]
    public int findPeakElement(int[] nums) {
		if(nums.length == 1) return 0;
		int lo = 0;
		int hi = nums.length - 1;
		while(lo<hi){
			int mid = lo + (hi - lo)/2;
			if(nums[mid]<nums[mid+1]) lo = mid + 1;//可能是mid+1位置，但不会是mid位置
			else hi = mid;//如果是mid - 1可能就找不到了。二分法总是有这样的判断
		}
		return lo;//hi也行，因为这种情况两者肯定是相等的
    }
}
