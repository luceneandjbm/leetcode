class Solution {
    //是个技巧性问题
    //假设现在这行数组是[1,3,3,1] ->[1,1,3,3,1]然后进行设置修改下标1,2,3的位置
    //得到[1,4,6,4,1]
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
		List<Integer> lst = new ArrayList<>();
		for(int i = 0; i < numRows; i ++){
			lst.add(0,1);
			//第一个和最后一个不动
			for(int j = 1; j < lst.size() - 1; j ++){
				lst.set(j,lst.get(j) + lst.get(j+1));
			}
			ans.add(new ArrayList<>(lst));
		}
		return ans;
    }
}
