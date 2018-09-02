class Solution {//双指针
    public int maxArea(int[] height) {
		//使用暴力解法也是能过的
		// int ans = 0;
		// int n = height.length;
		// for(int i = 0; i < n - 1; i ++){
		// 	for(int j = i + 1; j < n; j ++){
		// 		int min = Math.min(height[i],height[j]);
		// 		ans = Math.max(ans,(j-i)*min);
		// 	}
		// }
		// return ans;
        //O(n)的解法
        int i = 0;
		int j = height.length - 1;
		int max = 0;
		while(i < j){
			int min = Math.min(height[i],height[j]);
			max = Math.max(max,(j-i)*min);
			//往中间压缩，只要是比min小的那显然结果比上面的max小
			//因为height[i]<min,而且此时的(j-i')<(j-i)所以自然结果就会小于max
			while(i<j && height[i]<=min){
				i ++;
			}
			while(i < j && height[j] <= min){
				j --;
			}
		}
		return max;
    }
}
