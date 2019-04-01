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
    public int maxDepth(Node root) {
        if(root == null) return 0;
		List<Node> lst = root.children;
		int max = 1;//max一定是从1开始，因为有可能root是没有子树的此时list为空
		for(Node node : lst) {
			max = Math.max(max,maxDepth(node) + 1);
		}
		return max;
    }
}
