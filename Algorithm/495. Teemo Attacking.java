class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
		if(timeSeries.length == 0) return 0;
        int ans = 0;
		for(int i = 0; i < timeSeries.length - 1; i ++) {
			int time = timeSeries[i] + duration;
			if(time <= timeSeries[i+1]) {
				ans += duration;
			}else {
				ans += timeSeries[i+1] - timeSeries[i];
			}
		}
		return ans + duration;
    }
}

