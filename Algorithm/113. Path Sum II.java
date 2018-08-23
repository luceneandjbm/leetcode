
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
	Stack<Integer> stack = new Stack<>();
	List<List<Integer>> ans = new ArrayList<>();
	public void robot(TreeNode root, int target){
		if(root == null) return;
		if(root.left == null && root.right == null){
			if(root.val == target){
				List<Integer> lst = new ArrayList<>();
				for(int value : stack){
					lst.add(value);
				}
				lst.add(root.val);
				ans.add(lst);
			}
		}else {
			stack.push(root.val);
			robot(root.left,target-root.val);
			robot(root.right,target-root.val);
			stack.pop();
		}
	}
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        robot(root,sum);
		return ans;
    }
}

