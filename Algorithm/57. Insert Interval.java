/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {//这题初始区间无重叠很重要
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ans = new ArrayList<>();
		int n = intervals.size();
		int i = 0;
		while(i < n && intervals.get(i).end < newInterval.start) {
			ans.add(intervals.get(i ++));
		} 
		//newInterval是实时更新的
		while(i < n && intervals.get(i).start <= newInterval.end) {
			newInterval = new Interval(
				Math.min(newInterval.start,intervals.get(i).start),
				Math.max(newInterval.end,intervals.get(i).end));
			i ++;
		}
		ans.add(newInterval);
		while(i < n) ans.add(intervals.get(i ++));
		return ans;
    }
}
