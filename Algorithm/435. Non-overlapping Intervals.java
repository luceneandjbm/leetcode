/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {//先求最长区间，在使用长度去减该区间长度。类似于射箭问题
    public int eraseOverlapIntervals(Interval[] intervals) {
        if(intervals.length == 0) return 0;
        Arrays.sort(intervals,(a,b)->a.end - b.end);
		int len = 1;//注意是1开始
		int end = intervals[0].end;
		for(int i = 0; i < intervals.length; i ++){
			if(end <= intervals[i].start){//注意等于号
				len ++;
				end = intervals[i].end;
			}
		}
		return intervals.length - len;
    }
}
