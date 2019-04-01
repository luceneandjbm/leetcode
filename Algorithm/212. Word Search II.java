class Solution {//leetcode都是用的前缀树
    boolean[][] visited ;
	Set<String> ans = new HashSet<>();//使用set是为了去重，比如[["a","a"]],["a"]结果集就会有两个"a"
	public void robot(char[][] board, String s, int i, int j,int idx){
		if(i<0 || i>=board.length||j<0||j>=board[0].length || visited[i][j] || board[i][j] != s.charAt(idx))
			return;
		if(idx == s.length() - 1) {
			ans.add(s);
			return;
		}
		visited[i][j] = true;
		robot(board,s,i+1,j,idx + 1);
		robot(board,s,i-1,j,idx + 1);
		robot(board,s,i,j+1,idx + 1);
		robot(board,s,i,j-1,idx + 1);
		visited[i][j] = false;
	}
    public List<String> findWords(char[][] board, String[] words) {
		visited = new boolean[board.length][board[0].length];
        for(String word:words){
			for(int i = 0; i < board.length; i ++){
				for(int j = 0; j < board[0].length; j ++){
					if(board[i][j] == word.charAt(0)){
						robot(board,word,i,j,0);
					}
				}
			}
		}
		return new ArrayList<>(ans);
    }
}
