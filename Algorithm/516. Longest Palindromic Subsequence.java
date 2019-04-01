class Solution {
    int[][] cache;
	public int longestPalindromeSubseq(int st, int ed, String s) {
		if(st > ed) return 0;
		if(st == ed) return 1;
		if(cache[st][ed] != 0) return cache[st][ed];
		if(s.charAt(st) == s.charAt(ed)){
			return cache[st][ed] =longestPalindromeSubseq(st + 1, ed - 1, s) + 2;
		}else {
			return cache[st][ed] =  Math.max(longestPalindromeSubseq(st + 1, ed, s),
						longestPalindromeSubseq(st, ed - 1, s));
		}
	}
    public int longestPalindromeSubseq(String s) {
        if(s.length() <= 1)return s.length();
		cache = new int[s.length()][s.length()];
		return longestPalindromeSubseq(0, s.length() - 1, s);
    }
}
