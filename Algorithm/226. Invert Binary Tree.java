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
	public TreeNode robot(TreeNode root){
		if(root == null) return null;
		//反转左子树
		TreeNode left = robot(root.left);
		//反转右子树
		TreeNode right = robot(root.right);
		//交换左右子树
		root.left = right;
		root.right = left;
        return root;
	}
    public TreeNode invertTree(TreeNode root) {
		return robot(root);
    }
}
