class Solution {
    public int nthUglyNumber(int n) {
        
		PriorityQueue<Long> queue = new PriorityQueue<>();
		queue.offer(1l);
		for(int i = 0; i < n-1; i ++){
			long a = queue.poll();
			while(!queue.isEmpty() && queue.peek() == a){//去重
				queue.poll();
			}
			queue.offer(2*a);
			queue.offer(3*a);
			queue.offer(5*a);
		}
		return queue.poll().intValue();
    }
}
