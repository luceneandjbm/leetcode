class Solution {
    //拿到第一个字符串，与后面的一个一个去比较
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)return "";
		String str = strs[0];//假设第一个就是最长前缀
		for(int i = 1; i < strs.length; i ++){
			String s = strs[i];
			
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < str.length() && j < s.length(); j ++){
				if(str.charAt(j) == s.charAt(j)){
					sb.append(str.charAt(j));
				}else {
					break;
				}
			}
			//更新最长前缀
			str = sb.toString();
			if(str.length() == 0) return "";
		}
		return str;
    }
}
