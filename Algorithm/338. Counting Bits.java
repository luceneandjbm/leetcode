class Solution {
    public int[] countBits(int num) {
        //当i为偶数的时候，那么有f(i) = f(i/2),因为是左移一位后面补0得到的比如6(00000110)就是由3(00000011)右移一位
        //当i为奇数的时候，那么又f(i) = f(i-1)+1，因为奇数的前面一个肯定是偶数，在末尾补了0才得到的
        int[] dp = new int[num+1];
		for(int i = 0; i <= num; i ++){
			if(i%2 == 0)dp[i]  = dp[i/2];
			else dp[i] = dp[i-1]+1;
		}
		return dp;
    }
}
