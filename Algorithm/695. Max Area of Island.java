class Solution {
	public int robot(int i, int j, int[][] grid) {
		if(i >= 0&&i<grid.length&&j>=0&&j<grid[0].length&&grid[i][j]==1) {
			grid[i][j] = 0;
			return robot(i+1,j,grid) + robot(i-1,j,grid) + 
				robot(i,j+1,grid) +robot(i,j-1,grid) + 1;
		}
		return 0;
	}
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0) return 0;
		int m = grid.length;
		int n = grid[0].length;
		int max = 0;
		for(int i = 0; i < m; i ++) {
			for(int j = 0;j < n; j ++) {
				if(grid[i][j] == 1) {
					max = Math.max(max,robot(i,j,grid));
				}
			}
		}
		return max;
		
    }
}
