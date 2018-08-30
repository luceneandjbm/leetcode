/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {//要点：交换后两个节点，需要前面的一个辅助节点dummy->a->b
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode node = dummy;
		while(head != null && head.next != null){
			ListNode next = head.next;
			node.next = next;
			head.next = next.next;
			next.next = head;
			node = head;
			head = head.next;
			
		}
		return dummy.next;
    }
}	
