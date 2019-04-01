class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = m + n - 1;//最大元素的下标位置
		m--;
		n --;
		while(m >= 0&& n >= 0){
			if(nums1[m]>nums2[n]){
				nums1[idx--] = nums1[m--];
			}else {
				nums1[idx--] = nums2[n--];
			}
		}
		//如果nums2合并完了，那么nums1就是刚好有序的，否则就得继续合并nums2了
		while(n >= 0){
			nums1[idx--] = nums2[n--];
		}
    }
}
