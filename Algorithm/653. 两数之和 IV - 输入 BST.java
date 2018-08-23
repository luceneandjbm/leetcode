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
	//使用迭代的方式更加直观
    public boolean findTarget(TreeNode root, int k) {
        boolean ans = false;
		Stack<TreeNode> stack = new Stack<>();
		Set<Integer> set = new HashSet<>();
		while(root!=null || stack.size() > 0){
			while(root != null){
				stack.push(root);
				root = root.left;
			}
			if(stack.size() > 0){
				TreeNode node = stack.pop();
				//处理逻辑------
				if(set.contains(k - node.val)){
					ans = true;
					break;
				}
				set.add(node.val);
				//--------------
				root = node.right;
			}
		}
		return ans;
    }
}
