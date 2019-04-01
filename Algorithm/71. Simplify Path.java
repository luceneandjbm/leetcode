class Solution {
	//读懂就不难了，遇到..删除前面一个，遇到.则忽略
    public String simplifyPath(String path) {
        
		String[] strs = path.split("/");
		Stack<String> stack = new Stack<>();
		for(String str:strs) {
			if(str.equals("..")) {
				if(!stack.isEmpty())
					stack.pop();
			}else if(str.length() > 0 && !str.equals(".")){//字符串
				stack.push(str);
			}else {//空串和.忽略
				continue;
			}
		}
		return "/" + String.join("/",stack);
    }
}
