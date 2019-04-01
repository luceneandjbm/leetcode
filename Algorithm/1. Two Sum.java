class Solution {
    //使用hashmap，时间复杂度O(N)，空间复杂度O(N)
    //注意：使用排序，时间复杂度O(nlgn) 空间复杂度O(1)  但只能找到两个数找不到下标
    public int[] twoSum(int[] nums, int target) {
		HashMap<Integer,Integer> map = new HashMap<>();
		int[] ans = new int[2];
        for(int i = 0; i < nums.length; i ++){
			if(map.containsKey(target - nums[i])){//找到了
				ans[0] = map.get(target - nums[i]);
				ans[1] = i;
			}else {
				map.put(nums[i],i);
			}
		}
		return ans;
    }
}
