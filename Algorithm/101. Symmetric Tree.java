class Solution {
	
	public boolean robot(TreeNode left, TreeNode right){
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		if(left.val != right.val) return false;
		return robot(left.left,right.right) && robot(left.right,right.left);
	}
    public boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		return robot(root.left,root.right);
    }
}
