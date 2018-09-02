class Solution {
    public double findK(int[] nums1, int[] nums2, int k){
		int i = 0; 
		int j = 0;
		while(i < nums1.length && j < nums2.length){
			k --;
			if(nums1[i]  < nums2[j]){
				if(k == 0) return nums1[i];
				i ++;
			}else {
				if(k == 0) return nums2[j];
				j ++;
			}
		}
		return i == nums1.length?nums2[j + k -1]:nums1[i + k - 1];
	}
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
		int n = nums2.length;
		if((m + n) % 2!=0){//奇数个
			return findK(nums1,nums2,(m+n+1)/2);
		}else {
			return (findK(nums1,nums2,(m+n)/2)+findK(nums1,nums2,(m+n)/2+1))/2;
		}
    }
}
