/*
解题思路：字符串是由重复子串构成，说明重复子串的length必定小于等于
源字符串的一半，还有就是s.length() % length == 0。所以，把子串的长度
初始化为s.length()/2，然后循环判断。 
*/

class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for(int i = s.length()/2; i > 0; i --) {
			if(s.length() % i == 0) {
				String sub = s.substring(0,i);
				int count = s.length() / i;
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < count; j ++) {
					sb.append(sub);
				}
				if(sb.toString().equals(s)) return true;
			}
		}
		return false;
    }
}
