class Solution {
    public boolean isPerfectSquare(int num) {
        if(num ==0 || num == 1) return true;
        int hi = (int)Math.sqrt(num) + 1;
        int lo = 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(mid*mid > num) {
                hi = mid - 1;
            }else if (mid * mid < num) {
                lo = mid + 1;
            }else return true;
        }
        return false;
    }
}
