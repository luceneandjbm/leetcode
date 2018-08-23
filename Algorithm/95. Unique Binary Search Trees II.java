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
	
	public List<TreeNode> generateTrees(int start, int end){
		if(start > end){
			List<TreeNode> ans = new ArrayList<>();
			ans.add(null);
			return ans;
		}
		List<TreeNode> ans = new ArrayList<>();
		for(int i = start; i <= end; i ++){
			List<TreeNode> left = generateTrees(start,i-1);
			List<TreeNode> right = generateTrees(i+1,end);
			for(TreeNode l : left){
				for(TreeNode r : right){
					TreeNode root = new TreeNode(i);
					root.left = l;
					root.right = r;
					ans.add(root);
				}
			}
		}
		return ans;
	}
    public List<TreeNode> generateTrees(int n) {
        if(n < 1)return new ArrayList<>();
		return generateTrees(1,n);
    }
}
