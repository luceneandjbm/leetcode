class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        
		List<String> ans = new ArrayList<>();
		int n = words.length;
		if(n == 0) return ans;
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < n; i ++) {
			map.put(words[i],map.getOrDefault(words[i],0) + 1);
		}
		PriorityQueue<Map.Entry<String,Integer>> queue = new 
			PriorityQueue<>((e1,e2) -> e2.getValue() == e1.getValue()?
				e1.getKey().compareTo(e2.getKey()):e2.getValue() - e1.getValue());
		for(Map.Entry<String,Integer> e : map.entrySet()) {
			queue.offer(e);
		}
		for(int i = 0; i < k; i ++){
			ans.add(queue.poll().getKey());
		}
		return ans;
	}
}
