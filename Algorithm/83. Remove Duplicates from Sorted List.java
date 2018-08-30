/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//输入: 1->1->2->3->3
//输出: 1->2->3
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode node = head;
		while(node != null){
			while(node.next != null && node.next.val == node.val){
				node.next = node.next.next;
			}
			node = node.next;
		}
		return head;
    }
}
