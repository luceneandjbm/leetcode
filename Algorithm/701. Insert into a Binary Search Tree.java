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
	public TreeNode robot(TreeNode root, int val){
		if(root == null) return new TreeNode(val);
		if(root.val > val){
			root.left = robot(root.left,val);
		}else if(root.val < val){
			root.right = robot(root.right,val);
		}
		return root;
	}
    public TreeNode insertIntoBST(TreeNode root, int val) {
        return robot(root,val);
    }
}
