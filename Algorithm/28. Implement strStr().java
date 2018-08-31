class Solution {
    //haystack = "hello", needle = "ll"
    public int strStr(String haystack, String needle) {  
        if(haystack.length() < needle.length()) 
            return -1;
        if(needle.length() == 0)
            return 0;
		outer:
		for(int i = 0; i < haystack.length(); i ++){
			char c = haystack.charAt(i);
			//这行判断很重要，否则可能导致溢出
			if(i + needle.length() > haystack.length()) return -1;
			if(c == needle.charAt(0)){
				for(int j = 0; j < needle.length(); j ++){
					if(haystack.charAt(i+j) != needle.charAt(j))
						continue outer;
				}
				//走到这，说明找到了
				return i;
			}
		}
		return -1;
    }
}
