class Solution {
    public int[] maxSlidingWindow(int[] nums, int w) {
        if(nums.length == 0) return nums;
        LinkedList<Integer> queue = new LinkedList<>();
		int[] ans = new int[nums.length - w + 1];
		int idx = 0;
		for(int i = 0; i < nums.length; i ++){
			//要求严格递减
			while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
				queue.pollLast();
			}
			queue.offer(i);
			//是否过期
			if(i - w == queue.peek()){
				queue.poll();
			}
			//取值
			if(i - w + 1>=0){
				ans[idx ++] = nums[queue.peek()];
			}
		}
		return ans;
    }
}

