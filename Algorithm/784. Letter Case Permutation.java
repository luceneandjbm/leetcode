class Solution {
	List<String> ans = new ArrayList<>();
	String[] path;
	public void robot(int idx, String S){
		if(idx == S.length()){
			StringBuilder sb = new StringBuilder();
			for(String str : path){
				sb.append(str);
			}
			ans.add(sb.toString());
			return;
		}
		if(Character.isDigit(S.charAt(idx))){//数字
			path[idx] = String.valueOf(S.charAt(idx));//记录结果
			robot(idx + 1, S);
		}else {
			path[idx] = String.valueOf(S.charAt(idx)).toLowerCase();//记录结果
			robot(idx + 1, S);
			path[idx] = String.valueOf(S.charAt(idx)).toUpperCase();//记录结果
			robot(idx + 1, S);
		}
	}
    public List<String> letterCasePermutation(String S) {
        path = new String[S.length()];
		robot(0,S);
		return ans;
    }
}
