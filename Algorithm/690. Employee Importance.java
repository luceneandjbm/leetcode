/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {//注意：要求的不是直系的，而是直系的直系一层层往下求
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer,Employee> map = new HashMap<>();//id:员工
		for(Employee e : employees) {
			map.put(e.id,e);
		}
		int ans = map.get(id).importance;//自身不能忘了
        Queue<Integer> queue = new LinkedList<>();
		for(int idx : map.get(id).subordinates) {//该员工的下属的id集合
			queue.offer(idx);
		}
        while(!queue.isEmpty()) {
            Employee e = map.get(queue.poll());
            ans += e.importance;
            if(e.subordinates.size() > 0) {
                for(int idx : e.subordinates) {//该员工的下属的id集合
                    queue.offer(idx);
                }
            }
        }
		return ans;
    }
}
