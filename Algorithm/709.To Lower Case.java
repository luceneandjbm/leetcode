class Solution {
    public String toLowerCase(String str) {
        char[] chs = str.toCharArray();
		for(int i = 0; i < chs.length; i ++) {
			if(chs[i]>='A' && chs[i] <= 'Z') {
				chs[i] += 32;
			}
		}
		return String.valueOf(chs);
    }
}
