/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
	public Map<Integer,Integer> map = new HashMap<>();
	int max = 0;
	public void robot(TreeNode root){
		if(root == null)return;
		robot(root.left);
		//处理逻辑-----------
		map.put(root.val, map.getOrDefault(root.val,0) + 1);
		max = Math.max(map.get(root.val),max);
		//-------------------
		robot(root.right);
	}
    public int[] findMode(TreeNode root) {
        robot(root);
		List<Integer> lst = new ArrayList<>();
		for(Map.Entry<Integer,Integer> e : map.entrySet()){
			if(e.getValue() == max) lst.add(e.getKey());
		}
		int[] ans = new int[lst.size()];
		for(int i = 0; i < lst.size(); i ++){
			ans[i] = lst.get(i);
		}
		return ans;
    }
}	
