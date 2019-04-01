class Solution {
	//dp[i][j]表示A的i下标，B的j下标为结尾的最长公共子数组长度
	//注意：是子数组而不是子序列
    public int findLength(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i ++){
			if(A[i] == B[0]) dp[i][0] = 1;
		}
		for(int j = 1; j < n; j ++){
			if(B[j] == A[0]) dp[0][j] = 1;
		}
		int max = 0;
		for(int i = 1; i < m; i ++){
			for(int j = 1; j < n; j ++){
				if(A[i] == B[j])
					dp[i][j] = dp[i - 1][j-1] + 1;
				max = Math.max(max, dp[i][j]);
			}
		}
		return max;
    }
}
