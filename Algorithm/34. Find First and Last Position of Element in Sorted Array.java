class Solution {//先使用二分查找找到其中的一个位置，然后该位置往两端扩展
    public int[] searchRange(int[] nums, int target) {
        int lo = 0;
		int hi = nums.length - 1;
		int st = -1;
		int ed = -1;
		//首先是比较常规的二分查找，所以用了等于号
		while(lo <= hi){
			int mid = lo + (hi - lo) / 2;
			if(nums[mid] > target){
				hi = mid - 1;
			}else if (nums[mid] < target){
				lo = mid + 1;
			}else {//找到了
				st = mid;
				ed = mid;
				while(st-1>=0 && nums[st] == nums[st-1]) st --;
				while(ed + 1<=nums.length - 1 && nums[ed] == nums[ed+1]) ed ++;
                break;//一定不要忘了跳出循环
			}
		}
		int[] ans = new int[2];
		ans[0] = st;
		ans[1] = ed;
		return ans;
    }
}
