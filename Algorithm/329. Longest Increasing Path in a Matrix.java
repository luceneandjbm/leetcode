class Solution {
	
	int[][] distance = {{1,0},{-1,0},{0,1},{0,-1}};
    int[][] cache;
	public int robot(int i, int j, int[][] matrix){//从i,j位置能扩展出来的最大长度
		if(cache[i][j] > 0) return cache[i][j];
        int max = 1;
		for(int[] d : distance){
			int r = i + d[0];
			int c = j + d[1];
			if(r >= 0 && r < matrix.length && c >= 0 &&c < matrix[0].length
				&& matrix[r][c] > matrix[i][j]){
				max = Math.max(max, 1 + robot(r,c,matrix));	
			}
		}
        return cache[i][j] = max;
	}
    public int longestIncreasingPath(int[][] matrix) {
		if(matrix.length == 0)return 0;
		int m = matrix.length;
		int n = matrix[0].length;
        cache = new int[m][n];
		int max = 1;
		for(int i = 0; i < m; i ++){
			for(int j = 0; j < n; j ++){
				max = Math.max(max,robot(i,j,matrix));
			}
		}
		return max;
    }
}
