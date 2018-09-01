class Solution {//显然用优先队列能解决，但是可以自己写个排序，用归并（稳定）
    
    public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->b-a);
		for(int num : nums){
			queue.offer(num);
		}
		for(int i = 0; i < k-1; i ++){
			queue.poll();
		}
		return queue.poll();
    }
}


class Solution {//显然用优先队列能解决，但是可以自己写个排序，这里就用归并（稳定）
    
    public void merge(int[] nums, int st, int mid ,int ed){
		int i = st;
		int j = mid + 1;
		int idx = 0;
		int[] arr = new int[ed - st + 1];
		while(i<=mid&&j<=ed){
			if(nums[i]<nums[j]){
				arr[idx++] = nums[i++];
			}else{
				arr[idx++] = nums[j++];
			}
		}
		while(i<=mid){
			arr[idx++] = nums[i++];
		}
		while(j<=ed){
			arr[idx++] = nums[j++];
		}
		for(int k = 0; k < arr.length; k ++){
			nums[st+k] = arr[k];
		}
	}
    public void mergeSort(int[] nums,int st, int ed){
		if(st>=ed)return;
		int mid = (st + ed)/2;
		mergeSort(nums,st,mid);
		mergeSort(nums,mid + 1, ed);
		merge(nums,st,mid,ed);
	}
    public int findKthLargest(int[] nums, int k) {
		mergeSort(nums,0, nums.length - 1);
		int m = nums.length - k;
		return nums[m];
    }
}
