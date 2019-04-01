/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//这题跟最大深度是有区别的，虽然都是求到叶子节点长度
//但不能直接使用min(left,right)+1来求，比如某个节点有右孩子没有左孩子，这时候
//得到的深度是没有左孩子的情况，但事实上这个节点都不是叶子节点，所以应该求的是
//到右孩子的叶子节点的深度。才是该路径的最小深度
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
		if(root.left != null && root.right == null){
			return minDepth(root.left) + 1;
		}else if(root.left == null && root.right != null){
			return minDepth(root.right) + 1;
		}else {
			return Math.min(minDepth(root.left),minDepth(root.right)) + 1;
		}
    }
}

