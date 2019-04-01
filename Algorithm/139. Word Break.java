class Solution {
    boolean[] status;
	//idx处开始拆分
	public boolean robot(int idx, String s, List<String> wordDict){
		if(idx >= s.length()) return true;
        if(status[idx]) return false;
		for(int i = idx; i < s.length(); i ++){
			if(wordDict.contains(s.substring(idx,i+1))&&robot(i+1,s,wordDict))
				return true;
		}
        status[idx] = true;//访问过了，不能拆分出来
		return false;
	}
    public boolean wordBreak(String s, List<String> wordDict) {
        status = new boolean[s.length()];
        return robot(0,s,wordDict);
    }
}
