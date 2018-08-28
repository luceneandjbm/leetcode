class Solution {//这题以前是计划搜索做的,现在改dp
    //dp[i][j]表示从左上角做到i,j位置的路径数 dp[i][j] = dp[i-1][j]+dp[i][j-1]
    //因为(i,j)位置只能由它上面走过来和左边走过来
    public int uniquePaths(int m, int n) {
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i ++){
			dp[i][0] = 1;
		}
		for(int j = 1; j < n; j ++){
			dp[0][j] = 1;
		}
		for(int i = 1; i < m; i ++){
			for(int j = 1; j < n; j ++){
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
    }
}
