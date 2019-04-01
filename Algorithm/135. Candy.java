//我们先正向遍历一遍，对每个位置计算最少需要的糖果，注意：这里只考虑右边比左边大的情况
//比如[1,5,2,1]对应的糖果应该是[1,2,1,1]
//然后我们再倒着遍历一遍,计算每个位置至少需要的糖果，与上面的结果取较大值,
//这里结果是[1,3,2,1]与[1,2,1,1]对应位置取较大值，最终结果是[1,3,2,1]
class Solution {
    public int candy(int[] ratings) {
        if(ratings.length == 0) return 0;
        int n = ratings.length;
		int[] sum = new int[n];
		Arrays.fill(sum,1);//首先每个人得分一个
		//只考虑正向情况
		for(int i = 1; i < n; i ++){
			if(ratings[i]>ratings[i-1])sum[i] = sum[i-1] + 1;
		}
		for(int i = n - 2; i >= 0; i --){
			if(ratings[i] > ratings[i + 1]){
				sum[i] = Math.max(sum[i],sum[i+1] + 1);
			}
		}
		int ans = 0;
		for(int num : sum){
			ans += num;
		}
		return ans;
    }
}
