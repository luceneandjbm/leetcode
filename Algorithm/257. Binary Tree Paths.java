class Solution {
	List<String> ans = new ArrayList<>();
	public void robot(TreeNode root,String s){
		if(root.left == null && root.right == null){
			ans.add(s + root.val);
			return;
		}
		if(root.left != null){
			//注意不要写成s=s + root.val + "->",然后再传s到方法进去，这样返回到上一层s就不是原来的s了
			robot(root.left,s + root.val + "->");
		}
		if(root.right != null){
			robot(root.right,s + root.val + "->");
		}
		
	}
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return ans;
		robot(root,"");
		return ans;
    }
}
