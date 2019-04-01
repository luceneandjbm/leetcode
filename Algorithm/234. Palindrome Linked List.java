/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    //链表一分为二，将另一边进行反转，然后一个指向头一个指向尾，边往中间靠边比较
    public boolean isPalindrome(ListNode head) {
		if(head == null || head.next == null) return true;
        ListNode fast = head;
		ListNode slow = head;
		ListNode tmp = null;
		while(fast != null && fast.next != null){
			tmp = slow;
			fast = fast.next.next;
			slow = slow.next;
		}
		//进行截断  如果节点个数是偶数个那么左右各一半，是奇数的话那么右边多1
		tmp.next = null;
		//对右边进行反转,pre表示右边的头结点
		ListNode pre = null;
		ListNode node = slow;
		while(node != null){
			ListNode next = node.next;
			node.next = pre;
			pre = node;
			node = next;
		}
		while(head != null && pre != null){
			if(head.val != pre.val) return false;
			head = head.next;
			pre = pre.next;
		}
		return true;
    }
}
