/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
	//返回数组中最大元素所在下标
	public int getMaxIndex(int[] nums, int start, int end){
		int idx = start;
		for(int i = start; i <= end; i ++){
			if(nums[i]>nums[idx])idx = i;
		}
		return idx;
	}
	public TreeNode buildTree(int[] nums, int start, int end){
		if(start > end) return null;
		int idx = getMaxIndex(nums,start,end);
		TreeNode root = new TreeNode(nums[idx]);
		root.left = buildTree(nums,start,idx-1);
		root.right = buildTree(nums,idx + 1, end);
		return root;
	}
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums,0,nums.length-1);
    }
}
