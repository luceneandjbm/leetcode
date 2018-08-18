class Solution {
    //dp[i][j]表示S的前i个字符可以组成T的前J个字符串的种数
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(m<n) return 0;
        int[][] dp = new int[m + 1][n + 1];
        //s的前i个字符组成空串的种数是1,dp[0][i] = 0是肯定的
        for(int i = 0; i <= m; i ++) dp[i][0] = 1;
        for(int i = 1; i <= m; i ++) {
            for(int j= 1; j <= n; j ++) {
                dp[i][j] = dp[i-1][j] + 
                    (s.charAt(i-1) == t.charAt(j - 1)? dp[i -1][j - 1] : 0);
            }
        }
        return dp[m][n];
    }
}
