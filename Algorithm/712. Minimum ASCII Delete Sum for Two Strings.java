/*和编辑的最小代价类似
思路：dp[i][j]表示s1的前i个字符和s2的前j个字符删除部分字母组成相同串的最小删除
字符的ascii和：当s1[i-1]==s2[j-1]的时候dp[i][j]=dp[i-1][j-1]，当s1[i-1]!=s2[j-1]
时dp[i][j] = Math.min(dp[i-1][j]+s1.charAt(i-1),dp[i][j-1]+s2.charAt(j-1));
其实相等的时候肯定就该留下来，否则要删除的更多*/
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
		int n = s2.length();
		int[][] dp= new int[m+1][n+1];
		for(int i = 1; i < m + 1; i ++){
			dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
		}
		for(int j = 1; j < n+1; j ++){
			dp[0][j] = dp[0][j-1]  + s2.charAt(j-1);
		}
		for(int i = 1; i < m + 1; i ++){
			for(int j = 1; j < n + 1; j ++){
				if(s1.charAt(i-1) == s2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1];
				}else {
					dp[i][j] = Math.min(s1.charAt(i-1) + dp[i-1][j],
							s2.charAt(j-1) + dp[i][j-1]);
				}
			}
		}
		return dp[m][n];
    }
}

