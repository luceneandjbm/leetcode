class Solution {
	//dp[i]表示以i结尾的数组中，等差数列的个数 
    public int numberOfArithmeticSlices(int[] A) {
		if(A.length == 0)return 0;
		int n = A.length;
		int[] dp = new int[n];
        int ans = 0 ;
		//dp[0],dp[1]均为0
		for(int i = 2; i < n; i ++){
			if(A[i] - A[i-1] == A[i-1] - A[i-2]){
				dp[i] = dp[i-1]+1;
			}
            ans += dp[i];
		}
		return ans;
    }
}
