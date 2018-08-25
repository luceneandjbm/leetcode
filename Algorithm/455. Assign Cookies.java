class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
		Arrays.sort(s);
		int j = 0;
		//遍历饼干，尽量先满足胃口小的
		for(int i = 0; i < s.length && j < g.length; i ++){
			if(s[i] >= g[j]){
				j ++;
			}
		}
		return j;
    }
}
