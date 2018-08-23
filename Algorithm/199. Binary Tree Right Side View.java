/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//使用层序遍历，每次取最右边的即可
class Solution {
    
    public List<Integer> rightSideView(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if(root == null) return ans;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i ++){
				TreeNode node = queue.poll();
				if(i == size -1)ans.add(node.val);
				if(node.left != null) queue.offer(node.left);
				if(node.right != null) queue.offer(node.right);
			}
		}
		return ans;
    }
}
