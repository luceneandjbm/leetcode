class Solution {
    public List<String> summaryRanges(int[] nums) {
		if(nums.length == 0) return new ArrayList<>();
		List<String> lst = new ArrayList<>();
		if(nums.length == 1) {
			lst.add(String.valueOf(nums[0]));
			return lst;
		}
		int n = nums.length;
        int st = 0;
		int ed = 0;
		for(int i = 1; i < n; i ++) {
			if(nums[i] - nums[ed] == 1) {
				ed ++;
			}else {
				StringBuffer sb = new StringBuffer();
                if(st < ed) {
                    sb.append(nums[st]).append("->").append(nums[ed]);
                }else sb.append(nums[st]);
				lst.add(sb.toString());
				st = i;
				ed = i;
			}
		}
		StringBuffer sb = new StringBuffer();
        if(st < ed) {
            sb.append(nums[st]).append("->").append(nums[ed]);
        }else sb.append(nums[st]);
        lst.add(sb.toString());
		return lst;
    }
}
