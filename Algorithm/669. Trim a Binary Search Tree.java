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
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return null;
		if(root.val < L){//此时左子树都需要剪掉，修剪右子树即可
			root = trimBST(root.right,L,R);
		}else if(root.val > R){//此时右子树都不需要，修剪左子树
			root = trimBST(root.left,L,R);
		}else{//介于两者之间
			root.left = trimBST(root.left,L,R);
			root.right = trimBST(root.right,L,R);
		}
		return root;
    }
}
