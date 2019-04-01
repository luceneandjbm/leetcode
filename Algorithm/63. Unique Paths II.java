class Solution {
	//dp[i][j]表示到i，j位置的最大路径数
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0) return 0;
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i ++){
			if(obstacleGrid[i][0] == 1){
				break;
			}else {
				dp[i][0] = 1;
			}
		}//仍然要从0开始，否则会忽略掉0,0位置的1
		for(int j = 0; j < n; j ++){
			if(obstacleGrid[0][j] == 1){
				break;
			}else{
				dp[0][j] = 1;
			}
		}
		for(int i = 1; i < m; i ++){
			for(int j = 1; j < n; j ++){
				if(obstacleGrid[i][j] != 1){
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}else {
					dp[i][j] = 0;
				}
			}
		}
		return dp[m-1][n-1];
    }
	
}
