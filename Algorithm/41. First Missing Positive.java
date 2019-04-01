class Solution {
	//将每个元素放到他应该在的位置，比如3，它就应该放在第三个位置（即下标2）
    //第二次遍历发现nums[i] != i + 1那么i + 1就是消失的第一个数字
    public int firstMissingPositive(int[] nums) {
        int i = 0;
		int n = nums.length;
		while(i < n){
			if(nums[i] >= 1 && nums[i] <= n && nums[nums[i]-1] != nums[i]){
				int a = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				nums[i] = a;
			}else {
				i ++;
			}
		}
		for(int j = 0; j < n; j ++){
			if(nums[j] != j + 1) return j + 1;
		}
		return n + 1;
    }
}

