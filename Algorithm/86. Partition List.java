/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        
		ListNode dummy1 = new ListNode(-1);
		ListNode dummy2 = new ListNode(-2);
		ListNode d1 = dummy1;
		ListNode d2 = dummy2;
		while(head!=null){
			if(head.val < x){
				dummy1.next = head;
				dummy1 = dummy1.next;
			}else {
				dummy2.next = head;
				dummy2 = dummy2.next;
			}
			head = head.next;
		}
		dummy1.next = null;
		dummy2.next = null;
		dummy1.next = d2.next;
		return d1.next;
	}
}
