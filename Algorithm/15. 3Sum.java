class Solution {
    List<List<Integer>> ans = new ArrayList<>();
	public void robot(int idx, int[] nums){
		int i = idx + 1;
		int j = nums.length - 1;
		while(i < j){
			if(nums[idx] + nums[i] + nums[j]==0){
				List<Integer> lst = new ArrayList<>();
				lst.add(nums[idx]);
				lst.add(nums[i]);
				lst.add(nums[j]);
				ans.add(lst);
				//去重
				while(i<j&&nums[i+1]==nums[i]) i ++;
				while(i<j&&nums[j]==nums[j-1]) j --;
				i ++;
				j --;
			}else if(nums[idx] + nums[i] + nums[j]>0){
				j --;
			}else if(nums[idx] + nums[i] + nums[j]<0){
				i ++;
			}
		}
	}
    public List<List<Integer>> threeSum(int[] nums) {
		if(nums.length < 3) return ans;
       Arrays.sort(nums);
	   for(int i = 0; i < nums.length-2; i ++){
		   if(i>0&&nums[i] == nums[i-1])continue;
		   robot(i,nums);
	   }
	   return ans;
    }
}
