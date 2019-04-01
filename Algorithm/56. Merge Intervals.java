/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();
		if(intervals.size() == 0) return ans;
		Collections.sort(intervals,(a,b)->a.start - b.start);
		int st = intervals.get(0).start;
		int ed = intervals.get(0).end;
		for(int i = 1; i < intervals.size(); i ++){
			if(ed < intervals.get(i).start){
				ans.add(new Interval(st,ed));
				st = intervals.get(i).start;
				ed = intervals.get(i).end;
			}else {//产生了重叠，更新ed
				ed = Math.max(ed,intervals.get(i).end);
			}
		}
		ans.add(new Interval(st,ed));//别忘了加入最后一个
		return ans;
    }
}
