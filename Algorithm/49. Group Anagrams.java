class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
		Map<String,List<String>> map = new HashMap<>();
		if(strs.length == 0)return ans;
		for(int i = 0; i < strs.length; i ++){
			char[] arr = strs[i].toCharArray();
			Arrays.sort(arr);
			String s = String.valueOf(arr);
			if(!map.containsKey(s)) map.put(s,new ArrayList<>());
			map.get(s).add(strs[i]);
		}
		for(List<String> lst : map.values()){
			ans.add(lst);
		}
		return ans;
    }
}
