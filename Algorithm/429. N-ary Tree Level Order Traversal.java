/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> ans = new ArrayList<>();
		if(root == null) return ans;
        Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> lst = new ArrayList<>();
			for(int i = 0; i < size; i ++) {
				Node node = queue.poll();
                lst.add(node.val);
				List<Node> ns = node.children;
				for(Node n : ns) {
					if(n != null) queue.offer(n);
				}
			}
			ans.add(lst);
		}
		return ans;
    }
}
