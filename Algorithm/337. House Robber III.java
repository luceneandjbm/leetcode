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
	public static Map<TreeNode,Integer> tMap = new HashMap<>();
	public static Map<TreeNode,Integer> fMap = new HashMap<>();
	//打劫到这家可获得的最大金额
	public int robot(TreeNode root, boolean isRob){//状态为root和是否可偷
		if(root == null) return 0;
		if(isRob && tMap.get(root)!=null) return tMap.get(root);
		if(!isRob && fMap.get(root)!=null) return fMap.get(root);
		
		int ans = 0;
		if(isRob)
			ans = Math.max(ans,root.val + robot(root.left,false) + robot(root.right,false));
		ans = Math.max(ans,robot(root.left,true) + robot(root.right,true));
		if(isRob)tMap.put(root,ans);
		if(!isRob)fMap.put(root,ans);
        return ans;
	}
    public int rob(TreeNode root) {
        return robot(root,true);
    }
}
