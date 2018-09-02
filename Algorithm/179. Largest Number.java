class Solution {
    public String largestNumber(int[] nums) {
		if(nums.length == 0) return "";
		int n = nums.length;
        String[] strs = new String[n];
		for(int i = 0; i < n ; i ++){
			strs[i] = String.valueOf(nums[i]);
		}
		
		Arrays.sort(strs,(s1,s2)->(s2+s1).compareTo(s1+s2));
		if(strs[0].charAt(0) == '0') return "0";
		StringBuilder sb = new StringBuilder();
		for(String s : strs){
			sb.append(s);
		}
		return sb.toString();
    }
}
