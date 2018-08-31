class Solution {
	//主要做的两件工作，一件是去除空格，一件是判断第一个字符
    public int myAtoi(String str) {	
        str = str.trim();
		if(str.length() == 0) return 0;
		char first = str.charAt(0);
		StringBuilder sb = new StringBuilder();
		if(first == '+' || first == '-' ||Character.isDigit(first)){
			sb.append(first);
		}else {
			return 0;
		}
		for(int i = 1; i < str.length(); i ++){
			char c = str.charAt(i);
			if(Character.isDigit(c)){
				sb.append(c);
				if(Long.parseLong(sb.toString()) > Integer.MAX_VALUE)
					return Integer.MAX_VALUE;
                else if(Long.parseLong(sb.toString()) < Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;
			}else break;
		}
		if(sb.length() == 1 && 
				(sb.charAt(0) == '+' || sb.charAt(0) == '-'))
			return 0;
		return Integer.parseInt(sb.toString());
    }
}
