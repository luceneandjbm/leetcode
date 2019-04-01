//len[i]表示i结尾的最长递增的长度
//cnt[i]表示i结尾的最长递增的个数
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
		int n = nums.length;
		int[] len = new int[n];
		int[] cnt = new int[n];
		Arrays.fill(len,1);//长度至少是1
		Arrays.fill(cnt,1);//对应的个数至少也是1
		int max = 1;
		for(int i = 1; i < n; i ++){
			for(int j = 0; j < i; j ++){
				if(nums[i] > nums[j]){
					if(len[j] + 1 > len[i]){
						len[i] = len[j] + 1;
						cnt[i] = cnt[j];
					}else if(len[j] + 1== len[i]){
						cnt[i] += cnt[j];
					}
				}
			}
			max = Math.max(max,len[i]);
		}
		int ans = 0;
		for(int i = 0; i < n; i ++){
			if(len[i] == max) ans += cnt[i];
		}
		return ans;
    }
}
