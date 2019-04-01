/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
//先不考虑随机指针，将链表进行复制，但是每次都需要保存对应的节点
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        RandomListNode new_head = new RandomListNode(head.label);
		RandomListNode p = head;
		RandomListNode q = new_head;
		Map<RandomListNode,RandomListNode> map = new HashMap<>();
		map.put(p,q);
		//复制next指针
		while(p.next != null){
			q.next = new RandomListNode(p.next.label);
			p = p.next;
			q = q.next;
			map.put(p,q);
		}
		p = head;
		q = new_head;
		//复制随机指针
		while(p != null){
			q.random = map.get(p.random);
			p = p.next;
			q = q.next;
		}
		return new_head;
    }
}
