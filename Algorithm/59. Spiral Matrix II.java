class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
		int row_st = 0;
		int col_st = 0;
		int row_ed = n - 1;
		int col_ed = n - 1;
		int x = 1;
		while(row_st <= row_ed) {
            if(row_st == row_ed) {//处理最后只有一个元素的情况
                matrix[row_st][row_ed] = x ++;
            }else{
                for(int i = col_st; i < col_ed; i ++) {
                    matrix[row_st][i] = x ++;
                }
                for(int i = row_st; i < row_ed; i ++) {
                    matrix[i][col_ed] = x ++;
                }
                for(int i = col_ed; i > col_st; i --) {
                    matrix[row_ed][i] = x ++;
                }
                for(int i = row_ed; i > row_st; i --) {
                    matrix[i][col_st] = x ++;
                }
            }
            row_st ++;
            row_ed --;
            col_st ++;
            col_ed --;
            
		}
		return matrix;
    }
}




