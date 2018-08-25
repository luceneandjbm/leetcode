class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people,(a,b)->a[0]-b[0]!=0?b[0]-a[0]:a[1]-b[1]);
		//身高由高到低，相同身高则a[1]由小到大
		List<int[]> lst = new ArrayList<>();
		for(int[] p : people) {
			lst.add(p[1],p);
		}
		return lst.toArray(new int[people.length][2]);
    }
}
