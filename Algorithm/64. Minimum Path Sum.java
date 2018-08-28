class Solution {
	//dp[i][j]表示到i,j位置的最小路径和,只能从上面或者左边走过来
    public int minPathSum(int[][] grid) {
		if(grid.length == 0) return 0;
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		dp[0][0] = grid[0][0];
		for(int i = 1; i < m; i ++){
			dp[i][0] = dp[i-1][0] + grid[i][0];
		}
		for(int j = 1; j < n; j ++){
			dp[0][j] = dp[0][j-1] + grid[0][j];
		}
		for(int i = 1; i < m; i ++){
			for(int j = 1; j < n; j ++){
				dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
			}
		}
		return dp[m-1][n-1];
    }
}
