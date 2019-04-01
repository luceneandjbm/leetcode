/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
//区别与 116 完美二叉树
//使用层序遍历，116也可以，要求放入顺序先右后左
public class Solution {
    public void connect(TreeLinkNode root) {
		if(root == null) return;
		Queue<TreeLinkNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			TreeLinkNode next_node = null;
			for(int i = 0; i < size; i ++){
				TreeLinkNode node = queue.poll();
				node.next = next_node;
				if(node.right != null) queue.offer(node.right);
				if(node.left != null) queue.offer(node.left);
				next_node = node;
			}
		}
    }
}
