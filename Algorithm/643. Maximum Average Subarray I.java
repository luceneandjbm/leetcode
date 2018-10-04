class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double max = 0.0;
		for(int i = 0; i < k; i ++) {
			max += nums[i];
		}
        double m = max;//k长度的和
		for(int i = k; i < nums.length; i ++) {
			double x = m + nums[i] - nums[i-k];//窗口往后滑动了一位
			max = Math.max(max,x);
            m = x;
		}
		return max/k;
    }
}

