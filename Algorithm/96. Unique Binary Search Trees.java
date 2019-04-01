class Solution {
    int[] cache;
    public int robot(int n){
		if(n <= 1) return 1;
        if(cache[n]>0)return cache[n];
		int ans = 0;
		for(int i = 1; i <= n; i ++){
			int left = robot(i-1);//左子树种数
			int right = robot(n-i);//右子树种数
			ans += left*right;
		}
        cache[n] = ans;
		return ans;
	}
    public int numTrees(int n) {
        cache = new int[n+1];
		return robot(n);
    }
}   
