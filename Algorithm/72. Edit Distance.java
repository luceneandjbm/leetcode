//转换成字符串对齐问题dp[i][j]表示S的前i个字符和T的前j个字符对齐的最少得分
//比如ABCF和DBFG对齐不同的扣掉一份，-表示增加字符或者删除字符，也要扣掉一分
//ABCF-
//DB-FG
//dp[i][j]分三种情况，1、S的第i个字符和T的第j个字符去对齐。
//2、S的第i个字符和T的-去对齐。
//3、T的第j个字符和S的-去对齐。
class Solution {
    public int minDistance(String word1, String word2) {
        
		int m = word1.length();
		int n = word2.length();
		
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 0; i < m + 1; i ++){
			dp[i][0] = i;
		}
		for(int j = 1; j < n + 1; j ++){
			dp[0][j] = j;
		}
		for(int i = 1; i < m + 1; i ++){
			for(int j = 1; j < n + 1; j ++){
				int a = word1.charAt(i-1) == word2.charAt(j-1)?0:1;
				dp[i][j] = Math.min(dp[i-1][j-1]+a,
						Math.min(dp[i][j-1]+1,dp[i-1][j] + 1));
			}
		}
		return dp[m][n];
    }
}
