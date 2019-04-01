class Solution {
    public int compress(char[] chars) {
        int i = 0;
		int n = chars.length;
		int idx = 0;
		while(i < n) {
			char c = chars[i];
			int count = 0;
			while(i < n && chars[i] == c) {
				count ++;
				i ++;
			}
			chars[idx ++] = c;
			if(count > 1) {
				for(char ch : String.valueOf(count).toCharArray()) {
					chars[idx ++] = ch;
				}
			}
		}
		return idx;
    }
}
