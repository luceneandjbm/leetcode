/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //思路跟层序遍历是一样，不过得用到链表，每层遍历结果插入到表头即可
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
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
			ans.addFirst(lst);
		}
		return ans;	
    }
}
