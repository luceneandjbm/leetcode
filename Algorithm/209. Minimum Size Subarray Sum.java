class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0;
		int j = 0;
		int n = nums.length;
		int sum = 0;
		int min_len = n+1;
		while(j < n) {
			sum += nums[j];
			while(sum >= s) {
				min_len = Math.min(min_len,j - i + 1);
				sum -= nums[i++];
			}
            j++;
		}
		return min_len == n+1?0:min_len;
    }
}
