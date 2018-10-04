class Solution {
    public int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
		for(int x : nums) {//统计每个数字出现的个数
			map.put(x,map.getOrDefault(x,0)+1);
		}
		int ans = 0;
		if(k == 0) {
			for(int c : map.values()) {
				if(c > 1) ans ++;
			}
		}else {
			for(int key : map.keySet()) {
				//比如3,5是数组元素，k=2。下面这种计算不会出现重复
				if(map.containsKey(key - k)) ans ++;
			}
		}
		return ans;
    }
}



