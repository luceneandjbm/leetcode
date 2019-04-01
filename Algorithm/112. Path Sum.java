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
	public boolean robot(TreeNode root, int sum){
		if(root == null) return false;//找完了根节点还没找到
		if(root.left == null && root.right == null && sum == root.val)return true;
		return robot(root.left,sum - root.val) || robot(root.right,sum - root.val);
	}
    public boolean hasPathSum(TreeNode root, int sum) {
		return robot(root,sum);
	}
}
