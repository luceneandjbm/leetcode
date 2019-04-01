/*
n = 1时,10
n= 2时，十位上9个选择，个位9个选择。共 10 + 9*9 = 91
n = 3，百位9个选择，十位9个选择，个位8个选择。共10 + 9*9 + 9*9*8
n = 4，千位9个选择,百位9个选择,十位8个选择,个位7个选择。共10 + 9*9 + 9*9*8 + 9*9*8*7
n = k, 10 + 9*9 + 9*9*8 + 9*9*8*7 + ...+9*9*8*(10-k+1)
n > 2时候
f(n) = f(n-1)*(10-n+1)
*/
class Solution {
	int[] cache;
	public int robot(int n){
		if(n == 2) return 81;
		if(cache[n] != 0) return cache[n];
		return cache[n] = (10 - n + 1)*robot(n-1);
	}
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        if(n == 1) return 10;
		cache = new int[n+1];
		int ans = 10;
		for(int i = n; i >= 2; i --){
			ans += robot(i);
		}
        return ans;
    }
}
