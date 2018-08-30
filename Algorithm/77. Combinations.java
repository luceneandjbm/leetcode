class Solution {
	List<List<Integer>> ans = new ArrayList<>();
	int[] path;
	public void robot(int idx, int n, int k){
		if(k == 0){
			List<Integer> lst = new ArrayList<>();
			for(int i =path.length - 1; i >= 0; i --){
				lst.add(path[i]);
			}
			ans.add(lst);
			return;
		}
		
		for(int i = idx; i <= n; i ++){
			path[k-1] = i;
			robot(i+1,n,k-1);
		}
	}
    public List<List<Integer>> combine(int n, int k) {
		path = new int[k];
		robot(1,n,k);
		return ans;
    }
}
