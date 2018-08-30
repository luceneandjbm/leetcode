class Solution {
	//记录状态，解决重复计算导致的超时问题
	//比如aaxxxxbb sand   那么在计算sand的时候aaxxxxbb会计算一次
	//在计算and的时候aaxxxxbbs中的某个串还是会再次计算
	Map<String, List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
		if(map.containsKey(s))return map.get(s);
		
        List<String> ans = new ArrayList<>();
		//如果包含那么直接放入，我们是从后往前所以前面不会再有字符串了
		if(wordDict.contains(s)){
			ans.add(s);
		}
		//判断子串的情况
		for(int i = 1; i < s.length(); i ++){
			String sub = s.substring(i);
			if(wordDict.contains(sub)){
				List<String> lst = wordBreak(s.substring(0,i),wordDict);
				if(lst.size() > 0){
					for(String str : lst){
						ans.add(str + " " + sub);
					}
				}
			}
		}
		map.put(s,ans);
		return ans;
    }
}
