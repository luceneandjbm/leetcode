//dp[i][j]表示S的前i个字符组成T的前j个字符的方法数
//当s.charAt(i-1)==t.charAt(j-1)的时候有两种情况会转移到dp[i][j]状态
//1、取该字符：dp[i-1][j-1];2、不取该字符：dp[i-1][j]
//如果s.charAt(i-1)！=t.charAt(j-1)此时只能不取该字符，所以dp[i][j]=dp[i-1][j]

//补充：如果涉及到两个字符串或者数组取做dp的时候，那么一般都是考虑各自前m，n个
class Solution {
    public int numDistinct(String s, String t) {
        
		int m = s.length();
		int n = t.length();
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 0; i < m + 1; i ++){
			dp[i][0] = 1;//全都不取，所以是1
		}
		//s的前0个字符显然不能组成t的前j个字符（除了前0个）
		for(int j = 1; j < n + 1;j ++){
			dp[0][j] = 0;
		}
		for(int i = 1; i < m + 1; i ++){
			for(int j = 1; j < n + 1; j ++){
				if(s.charAt(i-1) == t.charAt(j-1)){
					//取s的第i个字符和不取两种情况
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}else {//只能不是不取s的第i个字符了
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[m][n];
    }
}
