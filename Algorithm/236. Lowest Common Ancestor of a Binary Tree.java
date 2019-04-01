class Solution {
	//p,q必在最近公共节点的两侧
	public TreeNode robot(TreeNode root, TreeNode p, TreeNode q){
		if(root == null || root == p || root == q)return root;
		TreeNode left = robot(root.left,p,q);//往左找
		TreeNode right = robot(root.right,p,q);//往右找
		//两者一定是位于最近公共祖先的两侧
		if(left != null && right != null) return root;
		return left == null?right : left;
	}
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return robot(root, p, q);
    }
}
