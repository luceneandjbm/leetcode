class Solution {
    // 输入: 1->2->3->4->5->NULL
    // 输出: 1->3->5->2->4->NULL
    public ListNode oddEvenList(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode odd = head;
		ListNode even = head.next;
		ListNode connect = even;
		//关键点在于是用even来进行判断而不能用odd,只要用了even来判断也能
		//同时满足odd的条件 因为even始终是在odd的后边
		//如果只有even!=null这个判断，那么有可能odd最后会指向null从而导致最后不能进行连接
		while(even != null && even.next != null){
			odd.next = even.next;
			odd = odd.next;
			even.next = odd.next;
			even = even.next;
		}
		odd.next = connect;
		return head;
    }
}
