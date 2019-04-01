/*dp[i][j]表示走到该位置最低应该有的血量（保证从该位置能走到最后）
显然需要从最后一个位置开始计算，因为最后一个位置才知道走到终点最少该有的血量

怎么转移到dp[i][j]呢？跟机器人走格子的题目一样，那题只能从上方一格走过来和左边一格
走过来。但是这题需要注意的是倒着计算的，所以i,j位置是由i+1,j位置和i，j+1位置计算的
1、能走到右边一格：dp[i][j] = Math.max(dp[i][j+1] - m[i][j],1);//血量不能为负
2、能走到下边一格：dp[i][j] = Math.max(dp[i+1][j] - m[i][j],1);
dp[i][j] = min(dp[i][j]);*/
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        
		int m = dungeon.length;
		int n = dungeon[0].length;
		int[][] dp = new int[m][n];
		dp[m-1][n-1] = dungeon[m-1][n-1] < 0?1-dungeon[m-1][n-1]:1;
		for(int i = m-2; i >= 0; i --){
			dp[i][n-1] = Math.max(dp[i+1][n-1] - dungeon[i][n-1],1);//保证血量不为负数
		}
		for(int j = n - 2; j >= 0; j --){
			dp[m-1][j] = Math.max(dp[m-1][j+1]-dungeon[m-1][j],1);
		}
		for(int i = m - 2; i >= 0; i --){
			for(int j = n - 2; j >= 0; j --){
				int a = Math.max(dp[i][j+1]-dungeon[i][j],1);
				int b = Math.max(dp[i+1][j]-dungeon[i][j],1);
				dp[i][j] = Math.min(a,b);
			}
		}
		return dp[0][0];
    }
}
