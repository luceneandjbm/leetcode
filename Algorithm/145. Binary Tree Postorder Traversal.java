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
	// private List<Integer> ans = new ArrayList<>();
	// public void postorder(TreeNode root){
	// 	if(root == null) return;
	// 	postorder(root.left);
	// 	postorder(root.right);
	// 	//------处理逻辑----------
	// 	ans.add(root.val);
	// }递归的做法
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> ans = new LinkedList<>();
		Stack<TreeNode> stack = new Stack<>();
		while(root != null || stack.size() > 0){
			while(root != null){
				stack.push(root);
				ans.addFirst(root.val);//与前序相反
				root = root.right;//与前序相反
			}
			if(!stack.isEmpty()){
				TreeNode node = stack.pop();
				root = node.left;//与前序相反
			}
		}
		return ans;
    }
}
