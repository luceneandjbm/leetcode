class Solution {
	int[] cache;
	public int robot(int n){
		if(n <= 3)return n;
        if(cache[n] > -1) return cache[n];
		return cache[n] = Math.max(2*robot(n-2),3*robot(n-3));
	}
    public int integerBreak(int n) {
        if(n <= 2) return 1;
		if(n == 3) return 2;
        cache = new int[n+1];
        Arrays.fill(cache,-1);
		return robot(n);
    }
}
