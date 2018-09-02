public class Solution{//有点类似于逆波兰
	public int calculate(String s) {
		Stack<Integer> stack = new Stack<>();
		int num = 0;
		char sign = '+';
		for(int i = 0; i < s.length(); i ++){
			char c = s.charAt(i);
			//1、数字的话就直接拼接起来
			if(Character.isDigit(c)){//比如102，三次运算就能获得整数102
				num = num * 10 + c - '0';//字符减使用了运算，那么就会转为整数运算
			}
			//运算符+-*/或者到最后一个字符的时候，执行运算
			if((!Character.isDigit(c) && c !=' ') || i == s.length() - 1){
				switch(sign){
					case '+':
						stack.push(num);
						break;
					case '-':
						stack.push(-num);
						break;
					case '*':
						stack.push(stack.pop()*num);
						break;
					case '/':
						stack.push(stack.pop()/num);
						break;
				}
				sign = c;
				num = 0;
			}
		}
		int ans = 0;
		for(int k : stack){
			ans += k;
		}
		return ans;
	}
 }
