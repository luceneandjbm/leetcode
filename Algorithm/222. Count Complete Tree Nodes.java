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
	public int getHeight(TreeNode root){
		if(root == null) return 0;
		return getHeight(root.left) + 1;
	}
    public int countNodes(TreeNode root) {
		if(root == null) return 0;
		int left_h = getHeight(root.left);//左子树高度
		int right_h = getHeight(root.right);//右子树高度
		if(left_h == right_h){
			return (int)Math.pow(2,left_h) + countNodes(root.right); 
		}else {//左边比右边高
			return (int)Math.pow(2,right_h) + countNodes(root.left); 
		}
    }
}
