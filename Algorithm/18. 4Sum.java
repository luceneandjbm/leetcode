class Solution {//区别与三数之和，这里在首尾进行控制保证不重复
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
		for(int i = 0; i < nums.length - 3; i ++) {
			if(i ==0 || nums[i] != nums[i-1]) {
				for(int j = n - 1; j - i >= 2; j --) {
					if(j == n - 1 || nums[j] != nums[j+1]) {
						int lo = i + 1;
						int hi = j - 1;
						
						while(lo<hi){
                            int sum = nums[i]+nums[lo]+nums[hi]+nums[j];
                            if(sum > target) hi --;
                            else if(sum < target) lo ++;
                            else {
                                ans.add(Arrays.asList(nums[i],nums[lo],nums[hi],nums[j]));
                                while(lo < hi && nums[lo]==nums[lo+1])lo ++;
                                while(lo < hi && nums[hi]==nums[hi-1]) hi --;
                                lo ++;
                                hi --;
                            }
                        }
					}
				}
			}
		}
		return ans;
    }
}
