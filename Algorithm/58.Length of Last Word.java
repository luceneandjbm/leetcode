class Solution {
    public int lengthOfLastWord(String s) {
        char[] chs = s.toCharArray();
		int i = chs.length - 1;
		int count = 0;
		while(i >= 0 && chs[i] == ' ') i --;
		while(i >= 0 && chs[i--] != ' ') count ++;
		return count;
    }
}

