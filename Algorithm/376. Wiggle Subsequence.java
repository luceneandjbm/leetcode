class Solution {
    public int wiggleMaxLength(int[] nums) {
        if(nums.length == 0) return 0;
		int n = nums.length;
		int[] down = new int[n];
		int[] up = new int[n];//表示到该i位置,结尾是正差的最长序列
		down[0] = 1;
		up[0] = 1;
		for(int i = 1; i < n; i ++){
			if(nums[i] > nums[i-1]){
				up[i] = down[i-1] + 1;
				down[i] = down[i-1];
			}else if(nums[i] < nums[i-1]){
				down[i] = up[i-1] + 1;
				up[i] = up[i-1];
			}else {
				up[i] = up[i-1];
				down[i]=down[i-1];
			}
		}
		return Math.max(up[n-1],down[n-1]);
    }
}
