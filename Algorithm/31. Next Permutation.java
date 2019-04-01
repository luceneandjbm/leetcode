/**假设数组nums长度为n（从0开始编号），数组中nums[i]到第nums[n-1]逆序
（降序排列），且nums[i-1]<nums[i]，则下一个全排列时只需要考虑nums[i-1]
到nums[n-1]即可，在i-1 右侧找到第一个大于nums[i-1] 的数，交换他们顺序，
则后面升序排列就是最小的数，即下一个全排列
*/
class Solution {
	public void swap(int[] nums, int i ,int j) {
		int x = nums[i];
		nums[i] = nums[j];
		nums[j] = x;
	}
    public void reverse(int[] nums) {
        int i = 0,j=nums.length - 1;
        while(i<j) {
            swap(nums,i++,j--);
        }
    }
    public void nextPermutation(int[] nums) {
        
		int n = nums.length;
		for(int i = n- 1; i >= 0; i --) {
			for(int j = n - 1; j > i; j --) {
				if(nums[j] > nums[i]) {
					swap(nums, i, j);
					Arrays.sort(nums,i + 1,n);
					return;
				}
			}
		}
		reverse(nums);
    }
}
