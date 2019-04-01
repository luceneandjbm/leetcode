class Solution {//[2,3,1,1,4]
    public boolean canJump(int[] nums) {
		int far = 0;//最远可以跳到哪里
		for(int i = 0; i < nums.length && i <= far; i ++){
			if(i + nums[i] > far) far = i + nums[i];
		}
		return far >= nums.length - 1;
    }
}
