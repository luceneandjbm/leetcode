class Solution {
	public boolean isMatch(String s, String word) {
		if(s.length() < word.length()) return false;
		int j = 0;
		for(int i = 0; i < s.length(); i ++) {
			if(s.charAt(i) == word.charAt(j)) {
				j ++;
			}
			if(j == word.length()) return true;
		}
		return false;
	}
    public String findLongestWord(String s, List<String> d) {
        
		String ans = "";
		for(String word : d) {
			if(isMatch(s,word)) {
				//前者表示要删除的字符更少
				if(word.length() > ans.length() ||(word.length()==ans.length()
						&&word.compareTo(ans)<0)) {
					ans = word;
				}
			}
		}
		return ans;
    }
}
