class Solution {
	
	//判断是否为回文串
	public boolean isValid(String s){
		int i = 0;
		int j = s.length() - 1;
		while(i<=j){
			if(s.charAt(i) != s.charAt(j)) return false;
			i ++;
			j --;
		}
		return true;
	}
	List<List<String>> ans = new ArrayList<>();
	String[] path;
    int len;
	public void robot(int idx, String s){//从idx处开始分
		
		//能走到这说明s在前面已经被按照成回文串分完了
		if(idx >= s.length()){
			List<String> lst = new ArrayList<>();
			for(int i = 0; i < len; i ++){
				lst.add(path[i]);
			}
			ans.add(lst);
			return;
		}
		
		for(int i = idx; i < s.length(); i++){
			String sub = s.substring(idx,i + 1);
			if(isValid(sub)){
				path[len++] = sub;
				robot(i + 1, s);
				len --;
			}
		}
		
	}
    public List<List<String>> partition(String s) {
		path = new String[s.length()];
		robot(0,s);
		return ans;
    }
}
