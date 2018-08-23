class Solution {//迭代
     
    public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		while(root != null || !stack.isEmpty()){
			while(root != null){
				stack.push(root);
				root = root.left;
			}
			if(!stack.isEmpty()){
				TreeNode node = stack.pop();
				//中序遍历处理逻辑------
				if(--k == 0) return node.val;
				//--------------
				root = node.right;
			}
		}
		return -1;
    }
}
