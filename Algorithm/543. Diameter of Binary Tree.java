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
	private int max = 0;
	//该函数返回的是该子树的最长直径
	private int robot(TreeNode root){
		if(root == null) return 0;
		int left = robot(root.left);//左子树直径
		int right = robot(root.right);//右子树直径
		max = Math.max(max,left + right + 1);
		return left>right?left + 1:right + 1;
	}
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        robot(root);
		return max - 1;
    }
}
