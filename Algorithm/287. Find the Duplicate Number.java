class Solution {
    //假如是1,2,3,4,5,5那么中间数是3,小于等于他的只有3个==mid但是此时3的左边插入一个
    //1-3之间的数的话，那么就是有count > mid了
    //假如是1,2,3,4,5,6,7那么中间数是4此时还是mid==count,但是插入一个数1-4在4左边就
    //成了count>mid了,如果是多个重复数(6,7变掉)那更是count>mid
    public int findDuplicate(int[] nums) {
        int lo = 1;//最小值
		int hi = nums.length - 1;//最大值
		while(lo < hi){
			int mid = lo + (hi - lo) / 2;
			int count = 0;
			for(int i = 0; i < nums.length; i ++){
				if(nums[i] <= mid) count ++;
			}
			if(count > mid) hi = mid;
			else lo = mid + 1;
		}
		return lo;
    }
}
