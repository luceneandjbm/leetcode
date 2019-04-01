/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//二叉搜索树的中序遍历就是从小到大的，那么反 遍历就是从大到小了
class Solution {
	int sum = 0;
	//先右后左那么就是反着中序遍历了
	public void robot(TreeNode root){
		if(root == null) return;
		robot(root.right);
		//处理逻辑--------------
		root.val += sum;
		sum = root.val;
		//---------------------
		robot(root.left);
	}
    public TreeNode convertBST(TreeNode root) {
        robot(root);
		return root;
    }
}
