class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
		//graph[i][j]表示课程ij是否有关系，即i->j
        int[][] graph = new int[numCourses][numCourses];
		int[] indegree = new int[numCourses];
		//初始化入度和邻接矩阵
		for(int[] pre : prerequisites){
			int to = pre[0];
			int from = pre[1];
			indegree[to] ++;
			graph[from][to] = 1;
		}
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < numCourses; i ++){
			if(indegree[i] == 0) queue.offer(i);
		}
		int count = 0;
		int[] ans = new int[numCourses];
		while(!queue.isEmpty()){
			int from = queue.poll();
			ans[count++] = from;
			for(int i = 0; i < numCourses; i ++){
				if(graph[from][i] == 1){
					if(--indegree[i] == 0) queue.offer(i);
				}
			}
		}
        int[] s = {};
		return count == numCourses?ans:s;
    }
}

