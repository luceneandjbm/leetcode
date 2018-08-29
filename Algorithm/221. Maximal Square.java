//dp[i][j]表示i,j为右下角的时候正方形的最大面积的边长max_size,当然如果
//matrix[i][j]==0的时候就是0了。当matrix[i][j]==1的时候，考虑三个地方
//左边dp[i][j-1]上边dp[i-1][j]和左上角dp[i-1][j-1]，三者应该取最小值然后+1
class Solution {
    public int maximalSquare(char[][] matrix) {
		if(matrix.length == 0) return 0;
        int m = matrix.length;
		int n = matrix[0].length;
		int[][] dp = new int[m][n];
		int max_size = 0;
		for(int i = 0; i < m; i ++){
			if(matrix[i][0] == '1') dp[i][0] = 1;
			max_size = Math.max(max_size,dp[i][0]);
		}
		for(int j = 1; j < n; j ++){
			if(matrix[0][j] == '1') dp[0][j] = 1;
			max_size = Math.max(max_size,dp[0][j]);
		}
		for(int i = 1; i < m; i ++){
			for(int j = 1; j < n; j ++){
				if(matrix[i][j] == '1'){
					dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1])) + 1;
				}
				max_size = Math.max(max_size,dp[i][j]);
			}
		}
		return max_size * max_size;
    }
}
