class Solution {
	public String minWindow(String s, String t) {
		int s_len = s.length();
		int t_len = t.length();
		int min_len = Integer.MAX_VALUE;
		int left = 0;
		int start = 0;
		int[] map = new int[60];
		//统计t中各个字符个数
		for(int i = 0; i < t_len; i ++){
			map[t.charAt(i) - 'A'] ++;
		}
		int count = 0;
		for(int i = 0; i < s_len; i ++){	
			if(-- map[s.charAt(i) - 'A']>=0) count ++;
			while(count == t_len){//找到了,注意是while循环不是if
				if(min_len > i - left + 1){
					min_len = i - left + 1;
					start = left;
				}
				if(++ map[s.charAt(left) - 'A'] >= 1) count --;
				left ++;
			}
		}
		return min_len == Integer.MAX_VALUE?"":s.substring(start,start + min_len);
	}
}
