//[2,2,2]
//[1,0,0],[1,0,1],[1,1,0]到这就发现了后两个是同一个集合，
//所以规定当两个元素一样的时候，前者一定要取，前者不取后者取肯定就有重复的了
class Solution {
	List<List<Integer>> ans = new ArrayList<>();
	boolean[] stauts;
	public void robot(int idx, int[] nums){
		if(idx == nums.length){
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < nums.length; i ++){
				if(stauts[i]) lst.add(nums[i]);
			}
			ans.add(lst);
			return;
		}
		
		if(idx > 0 && nums[idx] == nums[idx-1] && !stauts[idx-1]){
			stauts[idx] = false;
			robot(idx+1,nums);
		}else {
			stauts[idx] = true;
			robot(idx+1,nums);
			stauts[idx] = false;
			robot(idx+1,nums);
		}
	}
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
		stauts = new boolean[nums.length];
		robot(0,nums);
		return ans;
    }
}
