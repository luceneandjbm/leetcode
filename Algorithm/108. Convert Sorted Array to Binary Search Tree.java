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
    public TreeNode robot(int[] nums, int start, int end){
		if(start > end) return null;
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = robot(nums,start,mid - 1);//转换左子树
		root.right = robot(nums,mid + 1, end);//转换右子树
		return root;
	}
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
		return robot(nums, 0, nums.length - 1);
    }
}
