class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
		for(int num : nums1){
			map.put(num,map.getOrDefault(num,0) + 1);
		}
		List<Integer> lst = new ArrayList<>();
		for(int num : nums2){
			if(map.containsKey(num)){
				int l = map.get(num);
				lst.add(num);
				if(l==1){
					map.remove(num);
				}else {
					map.put(num,l - 1);
				}
			}
		}
		int[] ans = new int[lst.size()];
        for(int i = 0; i < lst.size(); i ++) {
            ans[i] = lst.get(i);
        }
		return ans;
    }
}
