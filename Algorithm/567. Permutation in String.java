class Solution {
	 //使用滑动窗口，窗口长度为s1的长度(l1)，从s2的下标为l1的地方开始滑动
    //每次右边窗口进入字符，左边出字符
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
		int len2 = s2.length();
		int[] map1 = new int[26];
		int[] map2 = new int[26];
		for(int i  = 0; i < len1; i ++) {
			map1[s1.charAt(i) - 'a'] ++;//统计每个字符个数
		}
		for(int i = 0; i < len2; i ++) {
			map2[s2.charAt(i) - 'a'] ++;//右边入窗
			
			if(i >= len1) {
				//左边出窗,然后容器里面的字符个数就=len1
				map2[s2.charAt(i-len1)-'a'] --;
			}
            if(Arrays.equals(map1,map2)) return true;//注意：不能放到if里面,因为可能第一个字符就满足了
		}
		return false;
    }
}
