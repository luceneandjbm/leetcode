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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if((p != null && q == null) ||(p == null && q != null))return false;
		if(p == null && q ==null) return true;//只是分支相等
		if(p.val != q.val) return false;
		//判断左子树是否相同，且右子树相等
		return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}
