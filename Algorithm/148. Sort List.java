/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {//进行归并排序，主要是怎么划分归并排序是有mid = (st + ed)/2的
	public ListNode merge(ListNode node1, ListNode node2){
		ListNode dummy = new ListNode(0);
		ListNode node = dummy;
		while(node1 != null && node2 != null){
			if(node1.val < node2.val){
				node.next = node1;
				node = node.next;
				node1 = node1.next;
			}else {
				node.next = node2;
				node = node.next;
				node2 = node2.next;
			}
		}
		while(node1 != null){
			node.next = node1;
			node = node.next;
			node1 = node1.next;
		}
		while(node2 != null){
			node.next = node2;
			node = node.next;
			node2 = node2.next;
		}
		return dummy.next;
	}
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
		ListNode fast = head;
		ListNode slow = head;
		ListNode tmp = null;
		while(fast != null && fast.next != null){
			tmp = slow;
			fast = fast.next.next;
			slow = slow.next;
		}
		//进行截断
		tmp.next = null;
		ListNode n1 = sortList(head);//对左边排序
		ListNode n2 = sortList(slow);//对右边排序
		return merge(n1,n2);//合并
    }
}
