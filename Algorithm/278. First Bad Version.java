public class Solution extends VersionControl {
    //版本情况应该是 false,false,false,flase,true,true,true，所以第一个错误版本
    //肯定是两个相邻位置的右边一个
    public int firstBadVersion(int n) {
        int lo = 1;
		int hi = n;
		while(lo < hi){
			int mid = lo + (hi - lo) / 2;
			if(!isBadVersion(mid)){
				lo = mid + 1;
			}else {
				hi = mid;//注意这里
			}
		}
		return lo;
    }
}
