class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
		Stack<TreeNode> lstack = new Stack<>();
		Stack<TreeNode> rstack = new Stack<>();
		lstack.push(root);
		while(lstack.size() > 0 || rstack.size() > 0){
			List<Integer> lst = new ArrayList<>();
			if(lstack.size() > 0){
				int size = lstack.size();
				for(int i = 0; i < size; i ++){
					TreeNode node = lstack.pop();
					lst.add(node.val);
					if(node.left != null) rstack.push(node.left);
					if(node.right != null) rstack.push(node.right);
				}
			}else{
				int size = rstack.size();
				for(int i = 0; i < size; i ++){
					TreeNode node = rstack.pop();
					lst.add(node.val);
					if(node.right != null) lstack.push(node.right);
					if(node.left != null) lstack.push(node.left);
				}
			}
			ans.add(lst);
		}
		return ans;
    }
}
