/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	//我们的做法是从前往后插入加入现在已经有了一定的顺序dummy(-1)->1->2->4
	//现在插入3 从dummy开始往后找到2的位置(node指针指向2)，然后再做简单的插入即可。
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
		ListNode dummy = new ListNode(-1);
		while(head != null){
			ListNode node = dummy;
			while(node.next != null && node.next.val < head.val){
				node = node.next;
			}
			ListNode next = head.next;
			head.next = node.next;
			node.next = head;
			head = next;
		}
		return dummy.next;
    }
}
