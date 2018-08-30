public class Solution {
	//lA > lB
    public ListNode getIntersectionNode(ListNode headA, ListNode headB,int l){
		for(int i = 0; i < l; i ++){
			headA = headA.next;
		}
		while(headA != null && headB != null){
			if(headA == headB) return headA;
			headA = headA.next;
			headB = headB.next;
		}
		return null;
	}
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
		
        ListNode nodeA = headA;
		int lA = 0;
		while(nodeA != null){
			lA ++;
			nodeA = nodeA.next;
		}
		ListNode nodeB = headB;
		int lB = 0;
		while(nodeB != null){
			lB ++;
			nodeB = nodeB.next;
		}
		ListNode ans = null;
		nodeA = headA;
		nodeB = headB;
		if(lA > lB){
			ans = getIntersectionNode(nodeA,nodeB,lA-lB);
		}else {
			ans = getIntersectionNode(nodeB,nodeA,lB-lA);
		}
		return ans;
    }
}
