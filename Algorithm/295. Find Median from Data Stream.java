class MedianFinder {
	PriorityQueue<Integer> q1 = new PriorityQueue<>();
	PriorityQueue<Integer> q2 = new PriorityQueue<>((a,b)-> b - a);
	int size = 0;
    /** initialize your data structure here. */
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
		size ++;
		q1.offer(num);
		q2.offer(q1.poll());
		if(q1.size() < q2.size()) q1.offer(q2.poll()); 
    }
    
    public double findMedian() {
        if(size % 2 == 0) return (q1.peek() + q2.peek() + 0.0)/2;
		else return (q1.peek() + 0.0);
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
