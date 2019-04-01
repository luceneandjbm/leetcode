class Solution {
    public boolean search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi) {
			int mid = (lo + hi)>>1;
			if(nums[mid] == target) return true;
			if(nums[lo] == nums[mid] && nums[hi] == nums[mid]){
				lo ++;
				hi --;
			//搜索左半部分
			}else if(nums[lo] <= nums[mid]){//注意nums[mid]此时肯定是!=target了
				if(nums[lo]<=target&&nums[mid]>target) hi = mid - 1;
				else lo = mid + 1;
			}else {
				if(nums[mid]<target && nums[hi]>=target)lo = mid + 1;
				else hi = mid - 1;
			}
		}
		return false;
    }
}
