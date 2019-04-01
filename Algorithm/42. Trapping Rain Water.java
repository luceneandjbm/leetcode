/*
分析某个柱子，发现该柱子上水的高度和其左右两边的最高柱子有关，
设该柱子左边所有柱子中最高的为leftmax，右边所有柱子中最高的为rightmax，
\如果min(leftmax, rightmax) 大于该柱子的高度，那么该柱子上可以蓄水为
min(leftmax, rightmax) - 该柱子高度，如果min(leftmax, rightmax) <= 该柱子高度，
则该柱子上没有蓄水。
可以从后往前扫描一遍数组求得某个柱子右边的最高柱子，从前往后扫描一遍数组求得
某个柱子左边的最高柱子, 然后按照上面的分析可以求得所有的蓄水量。
*/
class Solution {
    public int trap(int[] height) {
		int n = height.length;
		if(n == 0) return 0;
		//left_max[i]表示i柱子左边的最大高度
		int[] left_max = new int[n];
		int l_max = 0;
		for(int i = 0; i < n; i ++){
			left_max[i] = l_max;
			l_max = Math.max(l_max, height[i]);
		}
		int ans = 0;
		int r_max = 0;
		for(int i = n - 1; i >= 0; i --){
			
			int min_height = Math.min(left_max[i],r_max);
			if(min_height > height[i]){
				ans += min_height - height[i];
			}
			r_max = Math.max(r_max,height[i]);
		}
		return ans;
		
    }
}
