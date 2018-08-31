class MinStack {

    Stack<Integer> stack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();
    public MinStack() {
        
    }
    
    public void push(int x) {
        if(minStack.isEmpty()){
			minStack.push(x);
		}else if(minStack.peek()>=x){
			minStack.push(x);
		}
		stack.push(x);
    }
    
    public void pop() {
		int val = stack.pop();
		if(val == minStack.peek()){
			minStack.pop();
		}
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

