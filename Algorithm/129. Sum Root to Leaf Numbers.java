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
	public int sumNumbers(TreeNode root, int cur){//该节点到后续叶子节点的路径和
		if(root == null)return 0;
		int sum = 10*cur + root.val;
		//叶子节点直接返回
		if(root.left == null && root.right == null) return sum;
		return sumNumbers(root.left,sum) + sumNumbers(root.right,sum);
	}
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root,0);
    }
}
