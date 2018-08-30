/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }

 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return new TreeNode(head.val);//这个判断很重要，否则会空指针异常
		ListNode fast = head;
		ListNode slow = head;
		ListNode tmp = null;
		while(fast!= null && fast.next != null){
			tmp = slow;
			fast = fast.next.next;
			slow = slow.next;
		}
		//进行截断
		tmp.next = null;
		TreeNode root = new TreeNode(slow.val);
		root.left = sortedListToBST(head);
		root.right = sortedListToBST(slow.next);
		return root;
    }
}
 
