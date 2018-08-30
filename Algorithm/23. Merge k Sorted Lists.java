/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
		PriorityQueue<ListNode> queue = new PriorityQueue<>((a,b)->a.val-b.val);
		for(ListNode node : lists){
			if(node != null) queue.offer(node);
		}
		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		while(!queue.isEmpty()){
			ListNode node = queue.poll();
			head.next = node;
			head = head.next;
			if(node.next != null) queue.offer(node.next);
		}
		return dummy.next;
    }
}
