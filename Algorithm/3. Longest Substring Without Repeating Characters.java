class Solution {
    public int lengthOfLongestSubstring(String s) {
		if(s.length() == 0)return 0;
        Map<Character,Integer> map = new HashMap<>();//字符，下标
		int cur_len = 0;//当前无重复长度
		int max = 0;//最大长度
		int st = 0;//当前无重复字符串的开始下标
		for(int i = 0; i < s.length(); i ++){
			char c = s.charAt(i);
			if(!map.containsKey(c)){
				map.put(c,i);
				cur_len ++;
			}else {
				//找到前一个重复字符的位置
				int idx = map.get(c);
				//删除多余字符比如cabdma 那么需要删除前面的ca
				for(int j = st; j <= idx; j ++){
					map.remove(s.charAt(j));
				}
				//更新开始下标
				st = idx + 1;
				map.put(c,i);
				cur_len = i - idx;
			}
			max = Math.max(max,cur_len);
		}
		return max;
    }
}
