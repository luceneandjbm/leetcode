class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
		int count = 0;
		for(int num : nums) {
			if(num == 1) {
				count ++;
			}else count = 0;
			max = Math.max(count,max);
		}
		return max;
    }
}
