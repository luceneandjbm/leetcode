class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[n - 1];
        for(int i = 0; i < n-2; i ++) {
			int lo = i + 1; int hi = n - 1;
			while(lo < hi) {
				int sum = nums[i] + nums[lo] + nums[hi];
				if(sum > target) {
					hi --;
				}else {
					lo ++;
				}
				if(Math.abs(sum - target)<Math.abs(ans - target)){
					ans = sum;
				}
			}
		}
		return ans;
    }
}
