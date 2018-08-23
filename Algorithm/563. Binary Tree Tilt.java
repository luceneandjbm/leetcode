/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //要得到左右子树的绝对值差值，可以先求出左右子树节点和，再取差值的绝对值
class Solution {
	int sum = 0;
	//根据题目意思，整棵树的坡度为各个节点的坡度之和
	//我们的函数不是求节点坡度的，而是求节点的所有子节点之和,求解过程来计算坡度
	public int robot(TreeNode root){
		if(root == null) return 0;
		//左子树节点之和
		int left = robot(root.left);
		int right = robot(root.right);
		//累加坡度
		sum += Math.abs(left-right);
		return left + right + root.val;
	}
    public int findTilt(TreeNode root) {
        robot(root);
		return sum;
    }
}
