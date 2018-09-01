class Solution {
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;
		int lo = 0;
		int hi = nums.length - 1;
		//先找到最小元素所在位置
		while(lo < hi){
			int mid = lo + (hi - lo) / 2;
			//注意：需要取等号比如71的时候lo和mid落在了7上，hi落在了1上
            //此时应该是lo = mid + 1而不是hi = mid
			if(nums[mid]>=nums[lo] && nums[mid]>nums[hi]){
				lo = mid + 1;
			}else {
				hi = mid;//不能是mid - 1否则可能跳过了要找的数
			}
		}
		if(lo > 0){//说明数组是旋转过的
			if(target>=nums[0]&&target<=nums[lo-1]){
				hi = lo - 1;
				lo = 0;
			}else {
				hi = nums.length - 1;
			}
		}else {//数组没有经过旋转
			hi = nums.length - 1;
		}
		//进行常规的二分查找
		while(lo<=hi){
			int mid = lo + (hi - lo)/2;
			if(nums[mid] > target){
				hi = mid - 1;
			}else if(nums[mid] < target) {
				lo = mid + 1;
			}else {
				return mid;
			}
		}
		return -1;
    }
}
