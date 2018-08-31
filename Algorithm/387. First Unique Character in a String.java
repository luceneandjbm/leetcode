class Solution {
    public int firstUniqChar(String s) {
        if(s.length() == 0) return -1;
        int[] map = new int[26];
        Arrays.fill(map,-1);
		HashSet<Character> set = new HashSet<>();
		for(int i = 0; i < s.length() ; i++){
			char c = s.charAt(i);
			if(!set.contains(c)){
				set.add(c);
				map[c - 'a'] = i;
			}else {//出现重复，进行丢弃
				map[c - 'a'] = -1;
			}
		}
		int ans = s.length();
		for(int i = 0; i < 26; i ++){
			if(map[i] != -1){//没有重复
				ans = Math.min(ans, map[i]);
			}
		}
		return ans == s.length()?-1:ans;
    }
}
