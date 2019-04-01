class Solution {
    
    private int[] nums;
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] nums2 = Arrays.copyOf(nums,nums.length);
		Random r = new Random();
		for(int i = 0; i < nums.length; i ++){
			int j = r.nextInt(i + 1);
			swap(nums2,i,j);
		}
		return nums2;
    }
	public void swap(int[] nums, int i, int j){
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
    
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
