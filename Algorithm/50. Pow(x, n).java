class Solution {//要求时间复杂度为O(logn)
    public double myPow(double x, int n) {
		if(n == 0)return 1;
		double num = myPow(x,n/2);
		if(n%2==0){
			return num*num;
		}else {
			if(n>0) return num*num*x;
			else return num*num*(1/x);
		}
    }
}
