class Solution {
	
	StringBuilder sb = new StringBuilder();
	int[] path;
	int count;
    boolean[] status;
	public void robot(int idx, int n, int k){
		if(count > k)return;
		if(idx == n){
			count ++;//数量+1
			if(count == k){
				for(int i = 0; i < n; i ++){
					sb.append(path[i]);
				}
			}
			return;
		}
		for(int i = 1; i <= n; i ++){
			if(!status[i]){
				status[i] = true;
				path[idx] = i;
				robot(idx + 1, n, k);
				status[i] = false;
			}
		}
	}
    public String getPermutation(int n, int k) {
        path = new int[n];
        status = new boolean[n+1];
		robot(0,n,k);
		return sb.toString();
    }
}
