class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
		int j = b.length() - 1;
		int pre = 0;
		StringBuffer sb = new StringBuffer();
		while(i >= 0 || j >= 0) {
			int sum  = pre;
            if(i >= 0) sum += a.charAt(i--) - '0';
            if(j >= 0) sum += b.charAt(j --) - '0';
			sb.append(sum % 2);
			pre = sum/2;
		}
		if(pre != 0)sb.append(pre);
		return sb.reverse().toString();
    }
}


