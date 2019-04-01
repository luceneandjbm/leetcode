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
	public TreeNode findMin(TreeNode root){//最小节点必在最左边
		if(root == null) return null;
		if(root.left == null) return root;
		return findMin(root.left);
	}
    public TreeNode deleteNode(TreeNode root, int val) {
        if(root == null) return null;//没找到
		if(root.val > val){
			root.left = deleteNode(root.left,val);
		}else if(root.val < val){
			root.right = deleteNode(root.right,val);
		}else {//找到了
			if(root.left != null && root.right != null){//有两个孩子
				TreeNode minNode = findMin(root.right);//找到右子树中最小节点
				root.val = minNode.val;
				root.right = deleteNode(root.right,minNode.val);
			}else{//一个孩子或者没有孩子
				root = root.left != null?root.left:root.right;
			}
		}
		return root;
    }
}
