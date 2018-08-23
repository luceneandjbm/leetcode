/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *///层次遍历,每层找最大值即可
class Solution {
    public List<Integer> largestValues(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if(root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < size; i ++){
				TreeNode node = queue.poll();
				if(node.val > max) max = node.val;
				if(node.left != null) queue.offer(node.left);
				if(node.right != null) queue.offer(node.right);
			}
			ans.add(max);
		}
		return ans;
    }
}
