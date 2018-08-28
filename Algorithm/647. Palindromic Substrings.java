class Solution {
	//从i,j最多能扩展出来多少回文串
	public int countSubstrings(int i, int j, String s){
		int c = 0;
		while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
			i --;
			j ++;
			c ++;
		}
		return c;
	}
    public int countSubstrings(String s) {
		if(s.length() < 1) return 0;
		int ans = 0;
		for(int i = 0; i < s.length(); i ++){
			ans += countSubstrings(i,i,s);//aba  奇数个数
			ans += countSubstrings(i,i+1,s);//abba 偶数个数
		}
		return ans;
    }
}
