class Solution {
    public int longestConsecutive(int[] nums) {
		if(nums.length == 0) return 0;
		Set<Integer> map = new HashSet<>();
		for(int num : nums){
			map.add(num);
		}
		int ans = 1;
		for(int i = 0; i < nums.length; i ++){
			int count = 0;
			if(map.contains(nums[i])){
				count ++;
				int target = nums[i] + 1;//向上找的第一个值
				while(map.contains(target)){
					count ++;
					map.remove(target);//避免重复计算
					target ++;
				}
				target = nums[i] - 1;//向下找的第一个值
				while(map.contains(target)){
					count ++;
					map.remove(target);
					target --;
				}
				map.remove(nums[i]);
			}
			ans = Math.max(ans,count);
		}
		return ans;
    }
}
