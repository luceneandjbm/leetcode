class Solution {
	List<List<Integer>> ans = new ArrayList<>();
	int[] path;
	public void robot(int idx, int k, int n){
		if(n < 0) return;
		if(k == 0 && n != 0)return;
		if(k == 0 && n == 0){
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < path.length; i ++){
				lst.add(path[i]);
			}
			ans.add(lst);
			return;
		}
		
		for(int i = idx; i <= 9; i ++){
			path[k-1] = i;
			robot(i+1,k - 1, n - i);
		}
	}
    public List<List<Integer>> combinationSum3(int k, int n) {
        path = new int[k];
		robot(1,k,n);
		return ans;
    }
}
