class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer,List<Integer>> map = new HashMap<>();
		for(int i = 0;i < nums.length; i ++) {
			if(!map.containsKey(nums[i])) {
				map.put(nums[i],new ArrayList<>());
				map.get(nums[i]).add(i);
			}else {
				List<Integer> lst = map.get(nums[i]);
				for(int idx : lst) {
					if(Math.abs(i - idx) <= k) return true;
				}
				lst.add(i);
			}
		}
		return false;
    }
}
