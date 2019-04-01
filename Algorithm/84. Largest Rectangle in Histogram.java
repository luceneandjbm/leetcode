class Solution {
    public int largestRectangleArea(int[] heights) {
        
		Stack<Integer> stack = new Stack<>();
		int n = heights.length;
		int max = 0;
		for(int i = 0; i < n; i ++){
			while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
				int idx = stack.pop();
				int width = stack.isEmpty()?i : i - stack.peek() - 1;
				max = Math.max(max, heights[idx] * width);
			}
			stack.push(i);
		}
		//此时栈可能不为空，但是肯定高度都是递增的
		while(!stack.isEmpty()){
			int idx = stack.pop();
			int width = stack.isEmpty()?n:n - stack.peek() - 1;
			max = Math.max(max, heights[idx] * width);
		}
		return max;
    }
}
