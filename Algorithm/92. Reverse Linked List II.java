/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy, cur = head;
		for(int i = 0; i < m - 1; i ++){
			pre = pre.next;
			cur = cur.next;
		}
		ListNode next = cur.next;
		//cur始终不需要移动，一直指向的都是2,变化的只是next节点
		for(int i = 0; i < n - m; i ++){
			cur.next = next.next;
			next.next = pre.next;
			pre.next = next;
			next = cur.next;
		} 
		return dummy.next;
    }
}
