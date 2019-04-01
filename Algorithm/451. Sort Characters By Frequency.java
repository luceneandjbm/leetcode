class Solution {
    public String frequencySort(String s) {
        if(s.length() == 0) return s;
		int n = s.length();
		Map<Character,Integer> map = new HashMap<>();
		for(int i = 0; i < n; i ++) {
			map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0) + 1);
		}
		PriorityQueue<Map.Entry<Character,Integer>> queue = 
			new PriorityQueue<>((e1,e2)->e2.getValue() - e1.getValue());
		for(Map.Entry<Character,Integer> e : map.entrySet()) {
			queue.offer(e);
		}
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()){
			Map.Entry<Character,Integer> e = queue.poll();
            for(int i = 0; i < e.getValue(); i ++) {
                sb.append(e.getKey());
            }
		}
		return sb.toString();
    }
}
