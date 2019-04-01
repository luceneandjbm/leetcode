/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	//最简单的思路：先将链表反转，再相加，最后结果再反转
	public ListNode reverse(ListNode head){
		ListNode pre = null;
		while(head != null){
			ListNode next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = reverse(l1);
		ListNode n2 = reverse(l2);
		
		int pre  = 0;
		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		while(n1 != null || n2 != null){
			int sum = 0;
			sum += pre;
			if(n1!= null)sum += n1.val;
			if(n2 != null) sum += n2.val;
			head.next = new ListNode(sum%10);
            head = head.next;
			pre = sum / 10;
			if(n1 != null) n1 = n1.next;
			if(n2 != null) n2 = n2.next;
		}
		//进位了比如[5],[5]->[0,1]
		if(pre != 0){
			head.next = new ListNode(pre);
		}
		ListNode tmp = dummy.next;
		dummy.next = null;
		ListNode ans = reverse(tmp);
		return ans;
    }
}
