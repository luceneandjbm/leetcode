class Solution {
	
	List<Integer> ans = new ArrayList<>();
	public boolean robot(int idx, String S){
		if(idx >= S.length() && ans.size() >= 3) return true;
		for(int i = idx; i < S.length(); i ++){
			if(S.charAt(idx) == '0' && i > idx)return false;//说明有01xx这种情况
			String str = S.substring(idx,i+1);
			if(Long.parseLong(str) > Integer.MAX_VALUE) return false;
			int val = Integer.parseInt(str);
			int size = ans.size();
			if(size <= 1 || ans.get(size - 1) + ans.get(size - 2)==val){
				ans.add(val);
				if(robot(i+1,S)) return true;
				ans.remove(ans.size() - 1);
			} 
			if(size >= 2 && ans.get(size - 1) + ans.get(size - 2)<val){
				return false;
			}
		}
		return false;
	}
    public List<Integer> splitIntoFibonacci(String S) {
        boolean f = robot(0,S);
		if(f)return ans;
		else return new ArrayList<Integer>();
    }
}
