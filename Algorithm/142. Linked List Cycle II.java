/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 //假设有环：起点到环入口长度为a，从环入口到两者相遇处的长度记为c，相遇处到
 //环的入口长度为b，由于fast速度是slow的两倍所以有:2(a+c)=a+c+b+c -->a=b
 //所以当两者相遇的时候，slow从相遇处开始走，head总起点开始走，相遇处就是入口
public class Solution {
    public ListNode detectCycle(ListNode head) {
		if(head == null || head.next == null ) return null;
		ListNode fast = head;
		ListNode slow = head;
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow){//交点处
				ListNode t = head;
				while(slow != t){
					slow = slow.next;
					t = t.next;
				}
				return slow;
			}
		}
		return null;
    }
}
