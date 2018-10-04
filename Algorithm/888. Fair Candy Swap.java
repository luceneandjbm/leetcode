// Assume x needs to be removed and y needs to be added to A to equalize, so:
//  Sum(A) - x + y = Sum(B) - y + x
//  therefore, Sum(A) - Sum(B) = -2y + 2x
//  therefore, x = ((Sum(A) - Sum(B)) / 2) + y
//  lets call ((Sum(A) - Sum(B)) / 2), delta
//
class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
		int sumA = 0;
        for(int x : A) {
			sumA += x;
		}
		int sumB = 0;
		for(int x : B) {
			sumB += x;
		}
		int d = (sumA-sumB)/2;
		Set<Integer> set = new HashSet<>();
		for(int x:A) {
			set.add(x);
		}
		int[] ans = new int[2];
		for(int x : B) {
			if(set.contains(x + d)) {
				return new int[]{x+d,x};
			}
		}
		return ans;
    }
}
