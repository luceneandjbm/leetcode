class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Tuple> queue = new PriorityQueue<>((a,b)->a.val-b.val);
        for(int i = 0; i < matrix[0].length; i ++){//加入第一行元素
			queue.offer(new Tuple(0,i,matrix[0][i]));
		}
		for(int i = 0; i < k-1; i ++){
			Tuple t = queue.poll();
			if(t.x == matrix.length - 1) continue;
			//加入可能第二小的元素 也就是往下一个元素
			queue.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
		}
		return queue.poll().val;
    }
    class Tuple {
        int x;
        int y;
        int val;
        public Tuple(int x, int y, int val){
            this.x= x;
            this.y = y;
            this.val = val;
        }
    }
}
//使用二分
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0];
		int hi = matrix[matrix.length - 1][matrix[0].length - 1];
		while(lo < hi){
			int mid = lo + (hi - lo)/2;
			int count  = lessOrEqual(matrix,mid);
			if(count >= k) hi = mid;
			else lo = mid + 1;
		}
		return lo;
    }
	//小于等于target的元素个数
	private int lessOrEqual(int[][] matrix, int target){
		int i = matrix.length - 1;
		int j = 0;
		int count = 0;
		//从左下角开始统计
		while(i >= 0 && j < matrix[0].length){
			if(matrix[i][j]<=target){
				count += i + 1;
				j ++;
			}else {
				i --;
			}
		}
        return count;
	}
}
