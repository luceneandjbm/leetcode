//dp[i][j]表示s3的前i+j个字符是否能有s1的前i个字符和s2的前j个字符组成
//如何转移到dp[i][j]?
//1、当s1的第i个字符和s3的第i+j个字符相等且 s1的前i-1个字符和s2的前j个字符能组成
//s3的前i+j-1个字符时，那么dp[i][j]为true
//2、当s2的第j个字符，...,同 1
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        
		int n1 = s1.length();
		int n2 = s2.length();
		int n3 = s3.length();
		if(n1 + n2 != n3) return false;
		
		boolean[][] dp = new boolean[n1 + 1][n2 + 1];
		dp[0][0] = true;
		for(int i = 1; i < n1 + 1; i ++){
			if(s1.charAt(i-1) == s3.charAt(i - 1))
				dp[i][0] = true;
			else break;
		}
		for(int j = 1; j < n2 + 1; j ++){
			if(s2.charAt(j-1) == s3.charAt(j - 1))
				dp[0][j] = true;
			else break;
		}
		for(int i = 1; i < n1 + 1; i ++){
			for(int j = 1; j < n2 + 1; j ++){
				if((s1.charAt(i-1) == s3.charAt(i+j-1)&&dp[i-1][j])||
					(s2.charAt(j-1) == s3.charAt(i+j-1)&&dp[i][j-1]))
					dp[i][j] = true;
			}
		}
		return dp[n1][n2];
    }
}
