public class Solution {
    // 每次取n的最后一位添加到m（最初是0）的末尾,然后再移动m和n
    public int reverseBits(int n) {
        int m = 0;
		for(int i = 0; i < 32; i ++){
			m <<= 1;//注意：一定是先移动再添加，32位实际只能移动有意义的31次
			m = ((n>>i)&1) | m;
		}
		return m;
    }
}
