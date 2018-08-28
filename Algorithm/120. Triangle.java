class Solution {
    int[][] cache;
    public int robot(int i, int j, List<List<Integer>> triangle){
		if(i >= triangle.size())return 0;
		if(cache[i][j]!=0)return cache[i][j];
		return cache[i][j] = Math.min(robot(i+1,j,triangle),robot(i+1,j+1,triangle))
					+triangle.get(i).get(j);
	}
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0) return 0;
        cache = new int[triangle.size()][triangle.size()];
		return robot(0,0,triangle);
    }
}
