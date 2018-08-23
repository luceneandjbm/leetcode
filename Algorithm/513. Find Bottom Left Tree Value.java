/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//使用队列进行层次遍历
//或者先求数的最大深度得到len取最后一层即可
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int ans = root.val;
		while(queue.size() > 0){
			int size = queue.size();
			for(int i = 0; i < size; i ++){
				TreeNode node = queue.poll();
				if(i == 0){
					ans = node.val;
				}
				if(node.left != null)queue.offer(node.left);
				if(node.right != null)queue.offer(node.right);
			}
		}
		return ans;
    }
}
