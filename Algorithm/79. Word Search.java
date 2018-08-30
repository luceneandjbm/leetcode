class Solution {
	boolean[][] visited;
	public boolean robot(int i, int j, int idx, char[][] board, String word){
		if(i >= 0 && i < board.length && j >=0 && j < board[0].length&&idx<word.length()
			&&board[i][j] == word.charAt(idx) && !visited[i][j]){
			if(idx == word.length()-1) return true;//注意是n-1
			visited[i][j] = true;
			if(robot(i+1,j,idx + 1,board,word) || robot(i-1,j,idx + 1,board,word)
				||robot(i,j+1,idx + 1,board,word)||robot(i,j-1,idx + 1,board,word)){
				return true;//这种情况就不能还原现场了
			}
			visited[i][j] = false;
		}
		return false;
	}
    public boolean exist(char[][] board, String word) {
        if(board.length == 0) return false;
		visited = new boolean[board.length][board[0].length];
		for(int i = 0; i < board.length; i ++){
			for(int j = 0; j < board[0].length; j ++){
				if(board[i][j] == word.charAt(0)){
					if(robot(i,j,0,board,word)) return true;
				}
			}
		}
		return false;
    }
}
