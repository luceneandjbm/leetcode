class Solution {//"121474836472147483648"应该返回true
	List<Long> ans = new ArrayList<>();
	public boolean robot(int idx, String s){
		if(idx >= s.length()) {
			return ans.size() >= 3;
		}
		
		for(int i = idx; i < s.length(); i ++){
			if(s.charAt(idx) == '0' && i>idx)return false;
			String sub = s.substring(idx,i+1);
			if(sub.length() > 19) return false;
            long val = Long.parseLong(sub);
			int size = ans.size();
			if(size <= 1 || val == ans.get(size - 1) + ans.get(size-2)){
				ans.add(val);
				if(robot(i+1,s)) return true;
				ans.remove(ans.size() - 1);
			}
			if(size >= 2 && val > ans.get(size - 1) + ans.get(size-2)){
				return false;
			}
		}
		
		return false;
	}
    public boolean isAdditiveNumber(String num) {
        return robot(0,num);
    }
}
