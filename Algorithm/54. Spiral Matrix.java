class Solution {//这题跟顺时针打印矩阵差不多，采用分圈处理
	List<Integer> ans = new ArrayList<>();
	public void spiralOrder(int[][] matrix, int stRow, int stCol, int edRow, int edCol){
		//只有一行或者一列需要单独处理
		if(stRow == edRow){//只有一行
			for(int i = stCol; i <= edCol; i ++){
				ans.add(matrix[stRow][i]);
			}
            //注意使用else if，因为可能最后只有一个元素，使用两个if会导致多存一次
		}else if(stCol == edCol){//只有一列
			for(int i = stRow; i <= edRow; i ++){
				ans.add(matrix[i][stCol]);
			}
		}else {
			for(int i = stCol; i < edCol; i ++){
				ans.add(matrix[stRow][i]);
			}
			for(int i = stRow; i < edRow; i ++){
				ans.add(matrix[i][edCol]);
			}
			for(int i = edCol; i > stCol; i --){
				ans.add(matrix[edRow][i]);
			}
			for(int i = edRow; i > stRow; i --){
				ans.add(matrix[i][stCol]);
			}
		}
		
	}
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return ans;
		int m = matrix.length;
		int n = matrix[0].length;
		int stRow = 0;
		int stCol = 0;
		int edRow = m - 1;
		int edCol = n - 1;
		while(stRow <= edRow&&stCol<=edCol){
			spiralOrder(matrix, stRow ++,stCol ++, edRow -- , edCol --);
		}
		return ans;
    }
}
