class LRUCache {

	class Node{
		int key;
		int val;
		Node next;//后继节点
		Node pre;//前驱节点
		
	}
	Map<Integer,Node> map = new HashMap<>();
	int capacity;//缓存容量
	int size;//当前元素个数
	Node head;
	Node tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
		size = 0;
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.pre = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);
		if(node == null){
			return -1;
		}else{
			//删除链表中旧元素
			removeNode(node);
			//添加到表头
			insertBefore(node);
			return node.val;
		}
    }
	
    public void put(int key, int value) {
        Node node = map.get(key);
		if(node == null){
			size ++;
			node = new Node();
			node.val = value;
			node.key = key;
			//1、先放到表里面去
			map.put(key,node); 
			//2：放到链表中去
			insertBefore(node);
			//考虑是否过期
			if(size > capacity){
				//链表中删除最后的节点
				Node r = removeAfter();
				//表中删除
				map.remove(r.key);
				size --;
			}
		}else {
			node.val = value;
			//删除链表中旧元素
			removeNode(node);
			//添加到表头
			insertBefore(node);
		}
    }
	public void removeNode(Node node){
		node.pre.next = node.next;
		node.next.pre = node.pre;
	}
	public Node removeAfter(){
		Node node = tail.pre;
		tail.pre = tail.pre.pre;
		tail.pre.next = tail;
		return node;
	}
	public void insertBefore(Node node){
		head.next.pre = node;
		node.next = head.next;
		node.pre = head;
		head.next = node;
	}
}

