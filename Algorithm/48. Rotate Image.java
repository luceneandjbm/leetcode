class Solution {//这题n×n很重要，否则不能使用下面的方法
    //可以采用转圈处理左上角(0,0)右下角(n-1,n-1)；下一次就是(1,1),(n-2,n-2)
    //一直处理到最内
	public void rotate(int stRow, int stCol, int edRow, int edCol, int[][] matrix){
		int times = edRow - stRow;//旋转的次数
		for(int i = 0; i < times; i ++){
			int a = matrix[stRow][stCol+i];
			matrix[stRow][stCol+i] = matrix[edRow-i][stCol];
			matrix[edRow-i][stCol] = matrix[edRow][edCol-i];
			matrix[edRow][edCol-i] = matrix[stRow+i][edCol];
			matrix[stRow+i][edCol] = a;
		}		
	}
    public void rotate(int[][] matrix) {
        int stRow = 0, stCol = 0;
		int edRow = matrix.length - 1,edCol = matrix[0].length - 1;
		while(stRow < edRow){
			rotate(stRow ++, stCol ++, edRow --, edCol --, matrix);
		}
    }
}
