class Solution {
    //我们需要从后往前判断比如[1,8,9,9],如果判断某位是9那么就变成0，否则的话+1并返回
    //但是可以预遇到[9,9,9]这种情况，那么需要返回[1,0,0,0]
    public int[] plusOne(int[] nums) {
        int n = nums.length;
		for(int i = n - 1; i >= 0; i --){
			if(nums[i] == 9){
				nums[i] = 0;
			}else {
				nums[i]++;
				return nums;
			}
		}
		//走到这说明每位都是9了，最后需要进位
		int[] ans = new int[n + 1];
		ans[0] = 1;
		return ans;
    }
}
