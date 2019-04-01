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
	ArrayList<Integer> lst = new ArrayList<>();
	//level:哪一层 0开始    index:该节点下标  1开始，所以左子树下标是2*index,右子树2*index+1
	public int widthOfBinaryTree(TreeNode root, int level, int index) {
		if(root == null) return 0;
		//先遍历的是左子树，所以放入的肯定都是最左下标
		if(lst.size() == level){
			lst.add(index);
		}
		//该节点下标 - 该层最左边的下标 + 1 =>当前节点造成的宽度
		int cur_wid = index - lst.get(level) + 1;
		//分别求左子树最大宽度和右子树最大宽度
		int left_wid = widthOfBinaryTree(root.left,level + 1,index * 2);
		int right_wid = widthOfBinaryTree(root.right,level + 1,index * 2 + 1);
		return Math.max(cur_wid,Math.max(left_wid,right_wid));
	}
    public int widthOfBinaryTree(TreeNode root) {
        return widthOfBinaryTree(root,0,1);
    }
}
