//输入: nums = [1, 5, 1, 1, 6, 4]
//输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
//我们可以先进行排序[1,1,1,4,5,6]，然后将数组截成两半，但是需要注意的是
//如果数组元素个数是奇数个，我们要求前面一个比后面一半多一个，比如[1,3,5]
//拆分两半后就是[1,3]和[5]。如果是偶数就一半一半。然后遍历整个数组，依次从
//小的一半和大的一半取数
class Solution {
    public void wiggleSort(int[] nums) {
        int[] arr = Arrays.copyOf(nums,nums.length);
		Arrays.sort(arr);
		int mid = (nums.length + 1)/2 - 1;
		int end = nums.length - 1;
		int idx = 0;
		for(int i = 0; i < nums.length; i ++){
			if(i%2 == 0)
				nums[idx ++] = arr[mid --];
			else
				nums[idx ++] = arr[end --];
		}
    }
}
