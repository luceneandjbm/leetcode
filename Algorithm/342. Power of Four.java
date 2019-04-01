class Solution {
    public boolean isPowerOfFour(int num) {
        if(num == Integer.MIN_VALUE || num == Integer.MAX_VALUE) return false;
        int a = 1;
        for(int i = 0; i < 32; i += 2) {
            int x = a << i;
            if(x == num) return true;
        }
        return false;
    }
}
