/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    
	private void serialize(TreeNode root, StringBuilder sb){
		if(root == null){
			sb.append("#,");
			return;
		}
        //-----------处理逻辑---
		sb.append(root.val).append(",");
		//----------------------
		serialize(root.left, sb);
		serialize(root.right, sb);
	}
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
		StringBuilder sb = new StringBuilder();
		serialize(root,sb);
		return sb.toString();
    }
    //[1,2,#,#,3,#,#,]
	public TreeNode decode(Queue<String> queue){
		if(queue.isEmpty()) return null;
		String s = queue.poll();
		if(s.equals("#")) return null;
		TreeNode root = new TreeNode(Integer.valueOf(s));
		root.left = decode(queue);
		root.right = decode(queue);
		return root;
	}
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.equals("")) return null;
		String[] strs = data.split(",");
		Queue<String> queue = new LinkedList<>();
		for(String s : strs) 
			queue.offer(s);
		return decode(queue);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
