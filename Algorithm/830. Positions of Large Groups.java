class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        int st = 0;
		int ed = 0;
		List<List<Integer>> ans = new ArrayList<>();
		for(int i = 1; i < s.length(); i ++) {
			char c = s.charAt(i);
			if(c == s.charAt(i-1)) {
				ed ++;
			}else {
				if(ed - st + 1 >= 3) {
					List<Integer> lst = new ArrayList<>();
					lst.add(st);
					lst.add(ed);
					ans.add(lst);
				}
				st = ed = i;
			}
		}
		if(ed - st + 1 >= 3) {
			List<Integer> lst = new ArrayList<>();
			lst.add(st);
			lst.add(ed);
			ans.add(lst);
		}
		return ans;
    }
}
