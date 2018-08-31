class Solution {
    public boolean isValidSudoku(char[][] board) {
        
		//判断掉行和列
		for(int i = 0; i < 9; i ++){
			Set<Character> row = new HashSet<>();
			Set<Character> col = new HashSet<>();
			//每次判断掉一行和一列
			for(int j = 0; j < 9; j ++){
				if(board[i][j] != '.' && !row.add(board[i][j]))return false;
				if(board[j][i] != '.' && !col.add(board[j][i]))return false;
			}
		}
		//判断九宫格
		for(int i = 0; i < 7; i += 3){
			for(int j = 0; j < 7; j +=3){
				Set<Character> set = new HashSet<>();
				//每次判断掉一个九宫格
				for(int k = 0; k < 3; k ++){
					for(int l = 0; l < 3; l ++){
						if(board[i+k][j+l] != '.' && !set.add(board[i+k][j+l]))
							return false;
					}
				}
			}
		}
		return true;
    }
}
