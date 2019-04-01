//dp[i]表示i下标结尾的数字的最大整除子集的元素个数,类似于最长递增子序列，但是
//多了个还原过程,另外需要注意是子集而不是子序列，所以需要排序
//比如[8,4,2]那么结果应该是长度3的[2,4,8]
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        
		LinkedList<Integer> ans = new LinkedList<>();
		if(nums.length == 0) return ans;
		Arrays.sort(nums);
		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp,1);//最少是1
		int max = 1;
		for(int i = 1; i < n; i ++){
			for(int j = 0; j < i; j ++){
				if(nums[i] % nums[j] == 0){
					dp[i] = Math.max(dp[i],dp[j] + 1);
				}
			}
			max = Math.max(max, dp[i]);
		}
		int idx = 0;
		//找到最长子集的位置
		for(int i = 0; i < n; i ++){
			if(dp[i] == max){
				idx = i;
				break;
			}
		}
		ans.addFirst(nums[idx]);
		for(int i = idx - 1; i >= 0; i --){
			if(dp[i] == max - 1 && nums[idx]%nums[i]==0){
				max --;
				idx = i;
				ans.addFirst(nums[i]);
			}
		}
		return ans;
		
    }
}
