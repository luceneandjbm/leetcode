翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1
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
    public TreeNode invertTree(TreeNode root) {
		//是个后序遍历
        if(root == null) return null;
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		//处理逻辑
		root.left = right;
		root.right = left;
		return root;
    }
}
