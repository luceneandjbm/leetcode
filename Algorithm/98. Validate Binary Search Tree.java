/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {//中序遍历结果即为二叉搜索树结果 依次增大
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
		boolean ans = true;
		long pre = (long)Integer.MIN_VALUE - 1;
		while(root!=null || stack.size() > 0){
			while(root != null){
				stack.push(root);
				root = root.left;
			}
			if(!stack.isEmpty()){
				TreeNode node = stack.pop();
				if(node.val > pre){
					pre = node.val;
				}else {//该节点比它前一个小，不符合BST依次增大的规律
					ans = false;
					break;
				}
				root = node.right;
			}
		}
		return ans;
    }
}
