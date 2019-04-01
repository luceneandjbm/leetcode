class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();//元素，频率
		for(int num : nums){
			map.put(num,map.getOrDefault(num,0) + 1);
		}
		//按照频率由高到低排序  注意：堆的插入操作的最坏时间复杂度是O(logN）
		//但是n此插入构建堆的时间复杂度是O(N)的平均时间而不是O(NlogN)
		PriorityQueue<Map.Entry<Integer,Integer>> queue = 
			new PriorityQueue<>((a,b)->b.getValue() - a.getValue());
		for(Map.Entry<Integer,Integer> e : map.entrySet()){
			queue.offer(e);
		}
		List<Integer> ans = new ArrayList<>();
		for(int i = 0; i < k; i ++){
			ans.add(queue.poll().getKey());
		}
		return ans;
    }
}
