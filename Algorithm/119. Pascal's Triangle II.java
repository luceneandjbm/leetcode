class Solution {
    public List<Integer> getRow(int rowIndex) {
		List<Integer> lst = new ArrayList<>();
		for(int i = 0; i < rowIndex+1; i ++) {
			lst.add(0,1);
			for(int j = 1; j < i; j ++) {
				lst.set(j,lst.get(j)+lst.get(j+1));
			}
		}
		return lst;
    }
}
