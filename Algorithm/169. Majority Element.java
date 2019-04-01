class Solution {
    public int majorityElement(int[] nums) {
		int n = nums.length;
		Map<Integer,Integer> map = new HashMap<>();
		int max = 0;
		for(int i = 0; i < n; i ++){
			int count = map.getOrDefault(nums[i],0);
			if(count == 0){
				map.put(nums[i],1);
				count ++;
			}else {
				map.put(nums[i],count + 1);
				count ++;
			}
			if(count > n/2) return nums[i];
		}
		return -1;
    }
}
