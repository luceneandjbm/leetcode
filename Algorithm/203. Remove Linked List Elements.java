/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
class Solution {
    public ListNode removeElements(ListNode head, int val) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode newHead = dummy;
		while(head != null){
			if(head.val != val){
				dummy = dummy.next;
			}else {
				dummy.next = head.next;	
			}
			head = head.next;
		}
		return newHead.next;
    }
}
