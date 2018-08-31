class Solution {
	List<List<String>> ans = new ArrayList<>();
	boolean[] status;
	boolean[] incr;//斜率为正
	boolean[] decr;//斜率为负
	int[] path;
	
	public void robot(int idx, int n){//idx表示idx行
		if(idx >= n){
			List<String> lst = new ArrayList<>();
			for(int i = 0; i < n; i ++){
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < n; j ++){
					if(path[i] == j){
						sb.append("Q");
					}else {
						sb.append(".");
					}
				}
				lst.add(sb.toString());
			}
			ans.add(lst);
			return;
		}
		
		for(int i = 0; i < n; i ++){
			if(!status[i] && !incr[idx + i] && !decr[idx - i + n - 1]){//i列没有放
				status[i] = true;
				incr[idx + i] = true;
				decr[idx - i + n -1] = true;
				path[idx] = i;//idx行i列放了
				robot(idx + 1, n);
				status[i] =false;
				incr[idx + i] = false;
				decr[idx - i + n -1] = false;
			}
		}
		
	}
    public List<List<String>> solveNQueens(int n) {
        status = new boolean[n];
        incr = new boolean[2*n];
        decr = new boolean[2*n];
		path = new int[n];
		robot(0,n);
		return ans;
    }
}
