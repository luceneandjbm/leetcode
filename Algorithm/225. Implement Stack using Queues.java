class MyStack {
	Queue<Integer> in_queue = new LinkedList<>();
	Queue<Integer> out_queue = new LinkedList<>();
    /** Initialize your data structure here. */
    public MyStack() {
        
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        in_queue.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {//时间复杂度是O(N)不符合栈的pop操作
        while(in_queue.size() > 1) {
			out_queue.offer(in_queue.poll());
		}
		int x = in_queue.poll();
		while(out_queue.size() > 0) {
			in_queue.offer(out_queue.poll());
		}
		return x;
    }
    
    /** Get the top element. */
    public int top() {
        while(in_queue.size() > 1) {
			out_queue.offer(in_queue.poll());
		}
		int x = in_queue.poll();
		while(out_queue.size() > 0) {
			in_queue.offer(out_queue.poll());
		}
		in_queue.offer(x);
		return x;
    }
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return in_queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
