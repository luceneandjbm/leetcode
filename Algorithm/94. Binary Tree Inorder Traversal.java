// class Solution {
//     List<Integer> ans = new ArrayList<>();
// 	public void robot(TreeNode root) {
// 		if(root == null) return;
// 		robot(root.left);
// 		//----处理逻辑---
// 		ans.add(root.val);
// 		//---------------
// 		robot(root.right);
// 	} 
//     public List<Integer> inorderTraversal(TreeNode root) {
// 		robot(root);
//         return ans;
//     }
// }
class Solution {
     
    public List<Integer> inorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		List<Integer> ans = new ArrayList<>();
		while(root != null || stack.size() > 0){
			while(root != null){
				stack.push(root);
				root = root.left;
			}
			if(stack.size() > 0){
				TreeNode node = stack.pop();
				///----处理逻辑---
				ans.add(node.val);
				//----------------
				root = node.right;
			}
		}
        return ans;
    }
}
