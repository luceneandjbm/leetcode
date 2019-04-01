class Solution {
    public int findMinArrowShots(int[][] points) {
		if(points.length == 0) return 0;
        Arrays.sort(points,(a,b)->a[1]-b[1]);
		int count = 1;
		int end = points[0][1];
		for(int i = 0; i < points.length; i ++){
			if(end < points[i][0]){//加箭了
				count ++;
				end = points[i][1];
			}
		}
		return count;
    }
}

