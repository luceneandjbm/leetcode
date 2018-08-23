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
	int max = Integer.MIN_VALUE;
	public int robot(TreeNode root){//该路径下最大值
		if(root == null) return 0;
		int left = robot(root.left);
		int right = robot(root.right);
		left = Math.max(left,0);
		right = Math.max(right,0);
        max = Math.max(max, left + right + root.val);
		return left > right?left + root.val:right+root.val;
	}
    public int maxPathSum(TreeNode root) {
        robot(root);
		return max;
    }
}
