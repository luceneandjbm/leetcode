class Solution {
	
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
		if(root == null)return ans;
		Stack<TreeNode> stack = new Stack<>();
		while(root != null || stack.size() > 0){
			while(root != null){
				stack.push(root);
				//前序遍历处理逻辑
				ans.add(root.val);
				//--------------
				root = root.left;
			}
			if(!stack.isEmpty()){
				TreeNode node = stack.pop();
				root = node.right;
			}
		}
		return ans;
    }
}
