//题目没说清楚，要求应该是最长连续的)(()))() ->输出应该是4，也就是下标1-4的字符
class Solution {
    public int longestValidParentheses(String s) {
        if(s.length() == 0) return 0;
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < s.length(); i ++){
			char c = s.charAt(i);
			if(c == '('){
				stack.push(i);
			}else {
				if(stack.size() == 0){
					stack.push(i);
				}else {
					if(s.charAt(stack.peek()) == '('){
						stack.pop();
					}else {
						stack.push(i);
					}
				}
			}
		}
		int max = 0;
		int last = s.length();
		while(!stack.isEmpty()){
			int idx = stack.pop();
			int len = last - idx - 1;
			if(len > max) max = len;
			last = idx;
		}
		//如果last!=0，那么说明一开始首部就弹出了括号对
		if(last > max) max = last;
		return max;
    }
}
