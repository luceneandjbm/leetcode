class Solution {
	int max;
	int from;
	int to;
	public void robot(int st, int ed, String s){
		while(st >= 0 && ed < s.length() && s.charAt(st) == s.charAt(ed)){
			st --;
			ed ++;
		}
		int len = ed - st - 1;//回文串长度
		if(len > max){
			max = len;
			from = st + 1;
			to = ed - 1;
		}
	}
    public String longestPalindrome(String s) {
        if(s.length() == 0) return "";
		int ans = 1;
		for(int i = 0; i < s.length(); i ++){
			robot(i,i,s);//回文串元素个数是奇数的情况
			robot(i,i+1,s);//回文串元素的个数是偶数的情况
		}
		return s.substring(from, to + 1);
    }
}
