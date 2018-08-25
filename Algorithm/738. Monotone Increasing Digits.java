class Solution {//233
    public int monotoneIncreasingDigits(int N) {
        char[] c = String.valueOf(N).toCharArray();
		int j = c.length;
		for(int i = c.length - 1; i > 0 ; i --){
			if(c[i]<c[i-1]){
				c[i-1] --;
				j = i;
			}
		}
		for(int i = j; i < c.length; i ++){
			c[i] = '9';
		}
		return Integer.parseInt(String.valueOf(c));
    }
}
