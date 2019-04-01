/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//初始化的时候进行中序遍历，结构放入队列中
public class BSTIterator {
	Queue<Integer> queue;
    public BSTIterator(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
		queue = new LinkedList<>();
		while(root != null || stack.size() > 0){
			while(root != null){
				stack.push(root);
				root = root.left;
			}
			if(stack.size() > 0){
				TreeNode node = stack.pop();
				///----处理逻辑---
				queue.offer(node.val);
				//----------------
				root = node.right;
			}
		}
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return queue.size() > 0;
    }

    /** @return the next smallest number */
    public int next() {
        if(hasNext()){
			return queue.poll();
		}
		return -1;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
