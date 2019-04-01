class Solution {//没有重复元素很重要
    //要点：前序遍历的每个节点的左子树也是前序遍历,中序也是一样
    //从前序确定根节点，中序确定左子树节点个数
    public TreeNode buildTree(int[] preorder, int pre_st, int pre_ed,
							int[] inorder, int in_st, int in_ed){
		if(in_st > in_ed) return null;
		int i = in_st;
		for(; i <= in_ed; i ++){
			if(inorder[i] == preorder[pre_st])break;
		}
		int left_len = i - in_st;
		TreeNode root = new TreeNode(preorder[pre_st]);
		root.left = buildTree(preorder, pre_st+1, pre_st+left_len,
						inorder, in_st, i-1);
		root.right = buildTree(preorder, pre_st+left_len+1, pre_ed,
						inorder, i+1, in_ed);
		return root;
	}
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1,
				inorder, 0, inorder.length - 1);
    }
}  
