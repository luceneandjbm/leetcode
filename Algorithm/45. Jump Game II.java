class Solution {
    public int jump(int[] nums) {
        int step = 0;
		int pre = 0;
		int far = 0;
		while(far < nums.length-1){
			step ++;
			int st = pre;
			int ed = far;
			
			pre = far;
			//确定最远距离
			for(int i = st; i <= ed; i ++){
				if(i + nums[i] > far) far = i + nums[i];
			}
		}
		return step;
    }
}
