/*
数连续0的个数即可，需要注意的是在两边的0个中间的0的区别
比如两边的0是4个，那么可以种两颗。但是在中间的0是5的时候才能种两颗
所以两边的求法是：n/2，中间的颗数求法是(n-1)/2
*/
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int ans = 0;//种花的棵树
		int count = 1;//0的个数，注意一开始是从两边开始（左边）
		for(int f : flowerbed) {
			if(f == 0) {
				count ++;
			}else {
				ans += (count-1)/2;//所以最初count设置为1，从而得到的是n/2
				count = 0;
			}
		}
		if(count != 0){
			ans += count/2;//右边的情况
		}
		return ans >= n;
    }
}
