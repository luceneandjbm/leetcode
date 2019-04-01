class Solution {
    public int maxDistToClosest(int[] seats) {
        int d = 0;//一开始是从0开始，计算首部0的个数
		int ans = -1;
		for(int x : seats) {
			if(x == 1) {
                if(ans == -1) {//第一次遇到1
                    ans = Math.max(ans,d);//比如[0,0,0,1]这情况
                }else {
                    ans = Math.max(ans, d/2);
                }
				d = 1;//初始化重新计算,注意是从1开始比如[1,0,0,0,1]答案应该是2
			}else {
				d ++;
			}
		}
		return Math.max(ans, d-1);//可能最后的位置不是1
    }
}
