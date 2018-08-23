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
    public TreeNode robot(TreeNode root, int val){
		if(root == null) return null;
		if(root.val > val){//往左找
			return robot(root.left,val);
		}else if(root.val < val){//往右找
			return robot(root.right,val);
		}else {//找到了
			return root;
		}
	}
    public TreeNode searchBST(TreeNode root, int val) {
        return robot(root,val);
    }
}
