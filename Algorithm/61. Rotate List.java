/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
		int l = 0;
        ListNode node = head;
		while(node != null){
			l ++;
			node = node.next;
		}
		k = k % l;
		if(k == 0) return head;
		node = head;
		//找到断开点
		for(int i = 0; i < l - k - 1; i ++){
			node = node.next;
		}
		ListNode next_node = node.next;
		ListNode newHead = next_node;
		node.next = null;
		while(next_node.next != null){
			next_node = next_node.next;
		}
		next_node.next = head;
		return newHead;
    }
}
