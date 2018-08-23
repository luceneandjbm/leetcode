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
	int sum = 0;
    private void robot(TreeNode root){
		if(root == null)return;
		if(root.left!= null&&root.left.left==null&& root.left.right==null){
			sum += root.left.val;
			robot(root.right);
		}else {
			robot(root.left);
			robot(root.right);
		}
	}
    public int sumOfLeftLeaves(TreeNode root) {
        robot(root);
		return sum;
    }
}
