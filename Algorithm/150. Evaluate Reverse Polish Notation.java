class Solution {
//     输入: ["2", "1", "+", "3", "*"]
// 输出: 9
// 解释: ((2 + 1) * 3) = 9
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
		for(String token : tokens){
			int a = 0;
			int b = 0;
			switch(token){
				case "+":
					a = stack.pop();
					b = stack.pop();
					stack.push(a+b);
					break;
				case "-":
					a = stack.pop();
					b = stack.pop();
					stack.push(b-a);
					break;
				case "*":
					a = stack.pop();
					b = stack.pop();
					stack.push(a*b);
					break;
				case "/":
					a = stack.pop();
					b = stack.pop();
					stack.push(b/a);
					break;
				default:
					stack.push(Integer.parseInt(token));
					break;
			}
		}
		return stack.pop();
    }
}
