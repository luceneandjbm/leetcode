class Solution {
    public List<Integer> majorityElement(int[] nums) {
		List<Integer> ans = new ArrayList<>();
        if(nums.length == 0) return ans;
		if(nums.length == 1) {
			ans.add(nums[0]);
			return ans;
		}
		int c_a = nums[0];
		int c_b = nums[0];
		int count_a = 0;
		int count_b = 0;
		for(int num : nums) {
			if(num == c_a) count_a ++;
			else if (num == c_b) count_b ++;
			else if(count_a == 0) {
				c_a = num;
				count_a ++;
			}else if(count_b == 0) {
				c_b = num;
				count_b ++;
			}else {
				count_a --;
				count_b --;
			}
		}
		count_a = 0;count_b = 0;
		for(int num : nums) {
			if(num == c_a) count_a ++;
			else if(num == c_b) count_b ++;
		}
		
		if(count_a > nums.length/3)ans.add(c_a);
		if(count_b > nums.length/3)ans.add(c_b);
		return ans;
    }
}
