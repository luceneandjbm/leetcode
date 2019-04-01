class Solution {
    public static String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    List<String> ans = new ArrayList<>();
    char[] path;
	public void robot(int idx, String digits){
		if(idx >= digits.length()){
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < digits.length(); i ++){
				sb.append(path[i]);
			}
			ans.add(sb.toString());
			return;
		}
		
		int num = Integer.parseInt(String.valueOf(digits.charAt(idx)));
		String s = map[num];
		for(int i = 0; i < s.length(); i ++){
			path[idx] = s.charAt(i);
			robot(idx + 1, digits);
		}
	}
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return ans;
        path = new char[digits.length()];
		robot(0,digits);
		return ans;
    }
}
