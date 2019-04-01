class Solution {
    public String reverseVowels(String s) {
        if(s.length() == 0) return "";
		int i = 0;
		int j = s.length() - 1;
		Set<Character> set = new HashSet<>();
        char[] cs = {'a','A','e','E','i','I','o','O','u','U'};
        for(char c : cs) {
            set.add(c);
        }
		char[] chs = s.toCharArray();
		while(i <= j) {
			
			while(i<=j && !set.contains(chs[i])) i ++;
			while(i<=j && !set.contains(chs[j])) j --;
			if(i <= j) {
				char c = chs[i];
				chs[i] = chs[j];
				chs[j] = c;
			}
            i ++;
            j --;
		}
		return String.valueOf(chs);
    }
}
