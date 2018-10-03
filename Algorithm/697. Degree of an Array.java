class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer,List<Integer>> map = new HashMap<>();
		int n = nums.length;
		int max = 0;//度
		for(int i = 0;i < n; i ++) {
			int x = nums[i];
			if(!map.containsKey(x)) {
				map.put(x,new ArrayList<>());
			}
			List<Integer> lst = map.get(x);
			lst.add(i);
			max = Math.max(max,lst.size());
		}
		int min = n;
		for(List<Integer> lst : map.values()) {
			if(lst.size() == max) {
				min = Math.min(min,lst.get(lst.size() - 1) - lst.get(0)+1);
			}
		}
		return min;
    }
}


