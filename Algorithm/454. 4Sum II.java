class Solution {
	//先算出A[i] + B[i]的情况再算出C[i]+D[i]的情况
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
		for(int a : A){
			for(int b : B){
				int c = a + b;
				map.put(c,map.getOrDefault(c,0) + 1);
			}
		}
		int ans = 0;
		for(int c : C){
			for(int d : D){
				int s = -(c + d);
				ans += map.getOrDefault(s,0);
			}
		}
		return ans;
    }
}
