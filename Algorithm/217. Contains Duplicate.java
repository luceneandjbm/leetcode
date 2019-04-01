class Solution {
    //[1,2,3,1] ->true
	//这题方法比较多，比如使用hash表，排序
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
		for(int num : nums) {
			if(set.contains(num))return true;
			else set.add(num);
		}
		return false;
    }
}
