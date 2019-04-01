class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] map1 = new char[26];
		char[] map2 = new char[26];
		for(char c : ransomNote.toCharArray()) {
			map1[c - 'a'] ++;
		}
		for(char c : magazine.toCharArray()) {
			map2[c - 'a'] ++;
		}
		for(int i  = 0; i < 26; i ++) {
			if(map2[i] < map1[i]) return false;
		}
		return true;
    }
}



