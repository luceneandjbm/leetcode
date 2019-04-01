class Solution {
    public boolean isValid(String s) {
        if(s.length() == 0) return true;
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('(',1);
        map.put(')',2);
        map.put('[',4);
        map.put(']',5);
        map.put('{',7);
        map.put('}',8);
		Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i ++){
			char c = s.charAt(i);
			if(c == '('||c=='['||c=='{'){
				stack.push(c);
			}else {
				if(stack.isEmpty())return false;
				char t = stack.pop();
				if(map.get(c) - map.get(t) != 1)return false;
			}
		}
		return stack.isEmpty();
    }
}
