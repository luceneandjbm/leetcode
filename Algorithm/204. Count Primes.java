class Solution {//筛选法
    public int countPrimes(int n) {
        int ans = 0;
		boolean[] f = new boolean[n + 1];
		for(int i = 2; i < n; i ++){
			if(!f[i]){
				ans ++;
				//每次循环，将所有不为质数的数给排除掉
				for(int j = 1; j * i < n; j ++){
					f[i*j] = true;
				}
			}
		}
		return ans;
    }
}
