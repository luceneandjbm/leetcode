class Solution {
	int ans;
	boolean[] status;//path[idx]=i 表示在idx行的i列放了
	int[] path;//path[i] 表示i列有没有放
	boolean[] incr;//斜率为负即x+y=k
	boolean[] decr;//斜率为正即y-x=k    + n - 1是为了确保decr[i]始终为正数
	public void robot(int idx, int n){//idx表示列
		if(idx == n){
			ans ++;
			return;
		}
		for(int i = 0; i < n; i ++){
			if(!status[i] && !incr[idx + i] && !decr[i - idx + n - 1]){
				path[idx] = i;//idx行的i列放了
				status[i] = true;
				incr[idx + i] = true;
				decr[i - idx + n - 1] = true;
				robot(idx + 1, n);
				status[i] = false;
				incr[idx + i] = false;
				decr[i - idx + n - 1] = false;
			}
		}
	}
    public int totalNQueens(int n) {
        status = new boolean[n];
		path = new int[n];
		incr = new boolean[2*n];
		decr = new boolean[2*n];
		robot(0,n);
		return ans;
    }
}
