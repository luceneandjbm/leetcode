/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
        //1->2->3->4->5->NULL ->> 5->4->3->2->1->NULL
		//迭代的方式
			// public ListNode reverseList(ListNode head) {
			// ListNode pre = null;
			// while(head != null){
			// 	ListNode next = head.next;
			// 	head.next = pre;
			// 	pre = head;
			// 	head = next;
			// }
			// return pre;
			// } 
    //递归的方式
        public ListNode reverseList(ListNode head) {
            return reverseList(head,null);
        }      
		public ListNode reverseList(ListNode cur,ListNode pre) {
            if(cur == null) return pre;
			ListNode next = cur.next;
			cur.next = pre;
			return reverseList(next,cur);
        }
}
