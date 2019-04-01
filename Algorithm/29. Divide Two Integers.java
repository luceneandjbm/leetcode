class Solution {
    //输入: dividend = 7, divisor = -3
	//输出: -2
	public long robot(long dividend, long divisor){
		if(dividend < divisor) return 0;
		long sum = divisor;
		long c = 1;
		while(2 * sum < dividend){
			sum *= 2;
            c *= 2;
		}
		return c + robot(dividend - sum,divisor);
	}
    public int divide(int dividend, int divisor) {
		if(dividend == 0)return 0;
		int sign = 1;
		if((dividend>0&&divisor<0)||(dividend<0&&divisor>0)){
			sign = -1;
		}
		long a = (long)Math.abs((long)dividend);
		long b = (long)Math.abs((long)divisor);
		long ans = robot(a,b);
		if(ans > Integer.MAX_VALUE){
			if(sign==1) return Integer.MAX_VALUE;
			else return Integer.MIN_VALUE;
		}
		return (int)(sign*ans);
    }
}
