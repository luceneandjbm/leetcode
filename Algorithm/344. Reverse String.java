class Solution {
    public String reverseString(String s) {
		if(s.length() == 0) return "";
        char[] cs = s.toCharArray();
		int st = 0;
		int ed = cs.length - 1;
		while(st < ed) {
			char c = cs[st];
			cs[st] = cs[ed];
			cs[ed] = c;
			st ++;
			ed --;
		}
		return String.valueOf(cs);
    }
}
