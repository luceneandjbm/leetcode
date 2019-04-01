class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hash = new HashSet<>();
		HashSet<Integer> hash2 = new HashSet<>();
		
		for(int num : nums1) hash.add(num);//去重
		for(int num : nums2) hash2.add(num);//去重
		List<Integer> lst = new ArrayList<Integer>();
		for(int num : hash){
			if(hash2.contains(num)){
				lst.add(num);
			}
		}
		int[] ans = new int[lst.size()];
		int i = 0;
		for(int n : lst) {
			ans[i++] = n;
		}
		return ans;
    }
}
