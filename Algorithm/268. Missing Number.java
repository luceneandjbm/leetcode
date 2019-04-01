class Solution {
    //加入n=5那么序列应该是1,2,3,4,5  和就是15
    //如果缺失了一个3，那只需要用15减去1,2,4,5就得到了缺失的3
    public int missingNumber(int[] nums) {
        int n = nums.length;
		int s = n*(1+n)/2;
		int s2 = 0;
		for(int num : nums){
			s2 += num;
		}
		return s - s2;
    }
}
