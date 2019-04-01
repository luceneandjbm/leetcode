/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	//找到链表中点，然后后半部分反转从而将链表拆分为两部分1->2->3  4<-5<-6
	//再分别从表头开始合并
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		//保证前半部分节点数比后半部分多(奇数节点则多1，偶数节点则多2)
		ListNode tmp = slow;
		slow = slow.next;
		tmp.next = null;
		//对后半部分进行反转
		ListNode backHead = null;//后半部分头结点
		while(slow != null){
			ListNode next = slow.next;
			slow.next = backHead;
			backHead = slow;
			slow = next;
		}
		while(head != null && backHead != null){
			ListNode node1 = head.next;
			ListNode node2 = backHead.next;
			head.next = backHead;
			backHead.next = node1;
			head = node1;
			backHead = node2;
		}
    }
}
