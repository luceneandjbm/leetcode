/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 1->2->3->4->5, 和 n = 2.   -> 1->2->3->5.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
		ListNode node = head;
		for(int i = 0; i < n; i ++){
			node = node.next;
		}
        //删除头节点
        if(node == null) return head.next;
		ListNode pre = head;
		while(node.next != null){
			node = node.next;
			pre = pre.next;
		}
		pre.next = pre.next.next;
		return head;
    }
}
