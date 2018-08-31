class Solution {
	//主要做的两件工作，一件是去除空格，一件是判断第一个字符
    public int myAtoi(String str) {	
        str = str.trim();
		//空串直接返回
		if(str.length() == 0) return 0;
		char first = str.charAt(0);
		StringBuilder sb = new StringBuilder();
		//校验第一位是否满足条件，如果是字母那么就直接返回
		if(first == '+' || first == '-' || Character.isDigit(first)){
			sb.append(first);
		}else {
			return 0;
		}
		
		//校验后续是否满足条件,如果是字母就不再添加了
		for(int i = 1; i < str.length(); i ++){
			char c = str.charAt(i);
			if(Character.isDigit(c)){
				sb.append(c);
				if(Long.parseLong(sb.toString())>Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
				if(Long.parseLong(sb.toString())<Integer.MIN_VALUE){
					return Integer.MIN_VALUE;
				}
			}else {
				break;//遇到字母跳出循环
			}
		}
		
		//对于只有一位的情况进行判断
		if(sb.length() == 1 &&(sb.charAt(0)=='+'||sb.charAt(0)=='-'))
			return 0;
		return Integer.parseInt(sb.toString());
    }
}
