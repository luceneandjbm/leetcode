class Solution {
    //使用牛顿迭代法（基于泰勒级数）
    // 我们每次枚举一个值x，代入方程看是否为根，不是的话则将x的值变为： 
    // X=X−F(X)/F′(X)
    public int mySqrt(int num) {
        //x^x - num = 0 f(x) = x*x - num
		long x = num;//防止x*x溢出
		while(x*x>num){
			x = (x + num/x)/2;
		}
		return (int)x;
    }
}
