class Solution {
    public int searchInsert(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if(nums[mid] > target) hi = mid - 1;
            else if (nums[mid] < target) lo = mid + 1;
            else return mid;
        }
        //插入的数肯定是在nums[lo]和nums[hi]之间，此时hi会往后移动一位比如2,4中插入3最后肯定是hi指向2，lo指向3
        return lo;//或者返回hi + 1
    }
}
