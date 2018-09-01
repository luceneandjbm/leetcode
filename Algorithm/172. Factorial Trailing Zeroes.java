class Solution {
    // 0是由2×5得到的，所以统计n前面2和5的个数即可，又2的个数多余5的个数，
	//所以只需统计5的个数即可。还有一点要注意的就是25这种，5和5相乘的结果，
	//所以，还要看n/5里面有多少个5，也就相当于看n里面有多少个25，还有125，625.。
    public int trailingZeroes(int n) {
		int ans = 0;
		while(n/5>0){
			ans += n / 5;
			n /= 5;
		}
		return ans;
    }
}
