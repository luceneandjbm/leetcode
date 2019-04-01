class Solution {
    // 位运算的^有个特点n^n=0000...000全是0,所以a^b^b=a
    public int singleNumber(int[] nums) {
		int s = nums[0];
		for(int i = 1; i < nums.length; i ++){
			s^=nums[i];
		}
		return s;
    }
}
