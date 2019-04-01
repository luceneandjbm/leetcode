class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] graph = new int[numCourses][numCourses];//连接矩阵表示图
		int[] indegrees = new int[numCourses];//每个课程的入度
		//初始化入度和邻接矩阵
		for(int[] pre : prerequisites){
			int from = pre[1];
			int to = pre[0];
			indegrees[to] ++;//入度+1
			graph[from][to] = 1;
		}
		//维护入度为0的队列
		Queue<Integer> queue = new LinkedList<>();
		for(int i =0; i < numCourses; i ++){
			if(indegrees[i] == 0) queue.offer(i);
		}
		int count = 0;
		while(!queue.isEmpty()){//每次处理掉一个课程
			count ++;
			int from = queue.poll();//入度为0，只有出度
			for(int i = 0; i < numCourses; i ++){
				if(graph[from][i] != 0){//有关系需要减入度
					if(-- indegrees[i] == 0) queue.offer(i);
				}
			}
		}
		return count == numCourses;
    }
}
