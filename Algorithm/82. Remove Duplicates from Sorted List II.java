/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		while(head != null){
			while(head.next != null&&head.next.val == head.val){
				head = head.next;
			}
			if(pre.next != head){//说明存在了重复
				pre.next = head.next;
			}else {
				pre = pre.next;
			}
			head = head.next;
		}
		return dummy.next;
    }
}
