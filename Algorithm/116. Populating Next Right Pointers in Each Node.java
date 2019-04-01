public class Solution {//完美二叉树很重要
    //每次只处理左右孩子节点:左孩子指向右孩子，右孩子指向侄子
	public void robot(TreeLinkNode root){
		if(root == null) return;
        //不满足的话那就是叶子节点了
		if(root.left != null && root.right != null){
			root.left.next = root.right;
            if(root.next != null){//右边的是没有next节点的
                root.right.next = root.next.left;
            }
		}
		robot(root.left);
		robot(root.right);
	}
    public void connect(TreeLinkNode root) {
		robot(root);
    }
}
