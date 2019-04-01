class Solution {//维护一个最小值和第二小的值
	//一旦sec_min有值了,说明min肯定出现过比sec_min小的值，所以肯定存在a<b
	//所以出现num>sec_min的时候说明就存在a<b<c了
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
		int sec_min = Integer.MAX_VALUE;
		for(int num : nums){
			if(num <= min) min = num;//需要等号，否则可能出现 3 3 4这种答案
			else if (num < sec_min) sec_min = num;//<或者<=都可以的
			else if(num > sec_min) return true;//一定要>
		}
		return false;
    }
}
