class Solution {
	//从左到右遍历一遍 left[i] 表示下标i左边的所有数乘积 再从右往左遍历一遍right[i] 
	//表示右边的乘积，最后遍历计算即可
    //但是可以进行降维
    public int[] productExceptSelf(int[] nums) {
		
		int[] ans = new int[nums.length];//ans[i]表示i位置左边的乘积（不包括i位置）
		ans[0] = 1;//第一个元素的左边乘积是1
		for(int i = 1; i < nums.length; i ++){
			ans[i] = ans[i-1]*nums[i-1];
		}
		int right = 1;//最右边的元素的右边乘积也是1
		for(int i = nums.length - 1; i >= 0;i --){
			ans[i] = ans[i] * right;//左边的乘积*右边的乘积
			right = nums[i]*right;
		}
		return ans;
    }
}
