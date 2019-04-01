class Solution {
	public String reverse(String str) {
		char[] chs = str.toCharArray();
		int i = 0, j = chs.length - 1;
		while(i<j) {
			char c = chs[i];
			chs[i] = chs[j];
			chs[j] = c;
            i ++;
            j--;
		}
		return String.valueOf(chs);
	}
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
		StringBuffer sb = new StringBuffer();
		for(String str : strs) {
			sb.append(reverse(str)).append(" ");
		}
		return sb.toString().trim();
    }
}
