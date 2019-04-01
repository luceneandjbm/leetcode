class Solution {
   //  输入: [2,0,2,1,1,0]
	//输出: [0,0,1,1,2,2]
	public void swap(int[] nums, int lo, int hi){
		int tmp = nums[lo];
		nums[lo] = nums[hi];
		nums[hi] = tmp;
	}
    public void sortColors(int[] nums) {
        if(nums.length == 0) return;
		int lo =0;
		int hi = nums.length - 1;
		for(int i = 0; i < nums.length; i ++){
			//2全部挪到后面
			while(i <= hi && nums[i] == 2){
				swap(nums, i, hi);
				hi --;
			}
			//0全部挪到前面
			while(i >= lo && nums[i] == 0){
				swap(nums,i,lo);
				lo ++;
			}
		}
    }
}
