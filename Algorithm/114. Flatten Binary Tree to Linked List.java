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
    public void flatten(TreeNode root) {
        if(root == null) return;
		TreeNode left = root.left;
		TreeNode right = root.right;
		flatten(left);//左子树展开
		flatten(right);//右子树展开
		root.left = null;
		root.right = left;
		TreeNode node = root;
		while(node.right != null){
			node = node.right;
		}
		node.right = right;
    }
}
