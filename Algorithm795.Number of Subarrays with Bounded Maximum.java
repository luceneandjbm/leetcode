class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int ans = 0;
		int count = 0;
		int i = 0;
		for(int j = 0; j < A.length; j ++) {
			if(A[j] >= L && A[j] <= R) {//[2,3,4,1,1,5,2]L=2,R=3
				ans += j - i + 1;//包含A[j]的集合个数
				count = j - i + 1;
			}else if(A[j] < L) {
				ans += count;
			}else if(A[j]>R){
				count = 0;
				i = j + 1;
			}
		}
		return ans;
    }
}
