class Solution {
    //可以不使用额外数组，但是还是决定用简单的方法。先遍历一遍字符串，去掉所有符号
    //再遍历新的没有字符的字符串来判断是否是回文串
    public boolean isPalindrome(String s) {
        if(s.length() <= 1) return true;
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        for(int i = 0; i < s.length(); i ++) {
            if(Character.isLetter(s.charAt(i)) || 
                Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        int i = 0;
        int j = sb.length() - 1;
        String str = sb.toString();
        while(i < j) {
            if(str.charAt(i) != str.charAt(j)) return false;
            i ++;
            j --;
        }
        return true;
    }
}
