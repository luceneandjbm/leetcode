/**
先排好序，假设都是正数或者都是负数那么显然取后3位乘积
但是如果有正也有负比如-6,...,7。显然要么是最后面三个数，要么是前两个数
和最后一个数
*/

class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
		int n = nums.length;
		int ans = Math.max(nums[0]*nums[1]*nums[n-1],nums[n-3]*nums[n-2]*nums[n-1]);
		return ans;
    }
}
