class Solution {
	public int maximalRectangle(int[] height) {
		if(height.length == 0) return 0;
		Stack<Integer> stack = new Stack<>();
		int max = 0;
		for(int i = 0; i < height.length; i ++){
			while(!stack.isEmpty() && height[stack.peek()]>=height[i]){
				int idx = stack.pop();
				int width = stack.size()==0?i:i-stack.peek() - 1;
				max = Math.max(max,height[idx]*width);
			}
			stack.push(i);
		}
		while(!stack.isEmpty()){
			int idx = stack.pop();
			int width = stack.size() == 0?height.length:height.length - stack.peek()-1;
			max = Math.max(max,height[idx]*width);
		}
		return max;
	}
    public int maximalRectangle(char[][] matrix) {
		if(matrix.length == 0) return 0;
		
		int[] height = new int[matrix[0].length];
		int ans = 0;
		for(int i = 0; i < matrix.length; i ++){
			for(int j = 0; j < matrix[0].length; j ++){
				height[j] = matrix[i][j] == '1'?height[j] + 1:0;
			}
			ans = Math.max(ans,maximalRectangle(height));
		}
		return ans;
    }
}
