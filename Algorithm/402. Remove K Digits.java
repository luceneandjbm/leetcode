class Solution {
    public String removeKdigits(String num, int k) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < num.length(); i ++){
			while(k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)){
				k --;
				stack.pop();
			}
			stack.push(num.charAt(i));
		}
		//此时全是升序了，去除掉末尾的即可
		while(k-- > 0){
			stack.pop();
		}
        StringBuilder sb = new StringBuilder();
        for(char c : stack) {
            sb.append(c);
        }
		
		while(sb.length() > 0 && sb.charAt(0) == '0'){
			sb.deleteCharAt(0);
		}
		return sb.length() == 0?"0":sb.toString();
    }
}

