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
    public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList<>();
		if(root == null) return ans;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(queue.size() > 0){
			int size = queue.size();
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < size; i ++){
				TreeNode node = queue.poll();
				lst.add(node.val);
				if(node.left != null) queue.offer(node.left);
				if(node.right != null) queue.offer(node.right);
			}
			ans.add(lst);
		}
		return ans;	
    }
}
