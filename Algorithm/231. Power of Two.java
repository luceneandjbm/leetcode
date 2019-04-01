class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n == Integer.MAX_VALUE || n == Integer.MIN_VALUE) return false;
        int x = 1;
		for(int i  = 0; i < 32; i ++) {
			int a = x << i;
			if(a == n) return true;
		}
		return false;
    }
}
