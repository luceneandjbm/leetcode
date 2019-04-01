/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //后序遍历是左右中，即先处理完左子树再处理右子树
class Solution {
	public TreeNode buildTree(int[] inorder,int in_st, int in_ed, int[] postorder,
								int post_st,int post_ed){
		if(in_st > in_ed) return null;
		int i = in_st;
		for(;i <= in_ed;i++){
			if(inorder[i] == postorder[post_ed]) break;
		}
		int left_len = i - in_st;//左子树节点个数
		TreeNode root = new TreeNode(postorder[post_ed]);
		root.left = buildTree(inorder,in_st,in_st+left_len-1,postorder,post_st,post_st+left_len-1);
		root.right = buildTree(inorder,i+1,in_ed,postorder,post_st+left_len,post_ed-1);
// 		root.left = buildTree(inorder,in_st,i-1,postorder,post_st,post_st+len-1);或者这样
// 		root.right = buildTree(inorder,i+1,in_ed,postorder,post_st+len,post_ed-1);
		return root;
	}
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder,0,inorder.length - 1,postorder,0,postorder.length - 1);
    }
}

