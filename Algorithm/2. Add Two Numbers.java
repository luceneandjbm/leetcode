class Solution {
//     输入：(2 -> 5 -> 3) + (5 -> 6 -> 4)
    // 输出：7 -> 1 -> 8
    // 原因：352 + 465 = 817
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		int pre = 0;
		while(l1!=null||l2!=null){
			int sum = pre;
			if(l1 != null) sum += l1.val;
			if(l2 != null) sum += l2.val;
			ListNode node = new ListNode(sum%10);
			head.next = node;
			head = head.next;
			pre = sum/10;
			if(l1 != null) l1 = l1.next;
			if(l2 != null) l2 = l2.next;
		}
		if(pre != 0) head.next = new ListNode(pre);
		return dummy.next;
    }
}
