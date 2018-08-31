class Solution {
    //[1,2,3,4,5,6],k=3  -> [4,5,6,1,2,3] 可以发现分成了有序的两段[4,5,6],[1,2,3]
    //我们的做法是先完全旋转（交换）->[6,5,4,3,2,1]再对两部分进行旋转（交换）
    //[4,5,6]和[1,2,3]
    public void rotate(int[] nums, int start, int end) {
		while(start < end) {
			int tmp = nums[start];
			nums[start] = nums[end];
			nums[end] = tmp;
			start ++;
			end --;
		}
	}
    public void rotate(int[] nums, int k) {
        if(nums.length == 0 || k%nums.length ==0) return;
		k %= nums.length;
		//完全旋转
		rotate(nums, 0, nums.length - 1);
		rotate(nums,0,k - 1);
		rotate(nums,k,nums.length - 1);
    }
}
