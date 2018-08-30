class Solution {
	//输入：1->2->4, 1->3->4
	//输出：1->1->2->3->4->4
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		while(l1!=null&&l2!=null){
			if(l1.val < l2.val){
				dummy.next = l1;
				dummy = dummy.next;
				l1 = l1.next;
			}else {
				dummy.next = l2;
				dummy = dummy.next;
				l2 = l2.next;
			}
		}
		while(l1!=null){
			dummy.next = l1;
			dummy = dummy.next;
			l1 = l1.next;
		}
		while(l2!=null){
			dummy.next = l2;
			dummy = dummy.next;
			l2 = l2.next;
		}
		return head.next;
    }
}
