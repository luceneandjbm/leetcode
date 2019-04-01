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
	int max;
	//返回最长同值路径长度(该节点作为子节点)
	public int robot(TreeNode root){
		if(root == null)return 0;
		int left = robot(root.left);//左边同值长度
		int right = robot(root.right);//右边同值长度
		
		if(root.left == null || root.left.val != root.val){
			left = 0;
		}
		if(root.right == null || root.right.val != root.val){
			right = 0;
		}
		max = Math.max(max, left + right + 1);
		return left > right?left + 1:right+1;
	}
    public int longestUnivaluePath(TreeNode root) {
        if(root == null)return 0;
        robot(root);
		return max - 1;
    }
}
