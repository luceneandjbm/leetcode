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
	public TreeNode robot(TreeNode t1, TreeNode t2){
		if(t1 == null && t2 == null) return null;
		if(t1 == null) return t2;
		if(t2 == null) return t1;
		TreeNode root = new TreeNode(t1.val + t2.val);
		root.left = robot(t1.left,t2.left);//合并左子树
		root.right = robot(t1.right,t2.right);//合并右子树
		return root;
	}
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		return robot(t1,t2);
    }
}
