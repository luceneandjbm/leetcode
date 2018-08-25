class Solution {//参考http://www.cnblogs.com/grandyang/p/7098764.html
    //首先题目意思是  两个相同的任务直接应该有n个时间的间隙
    //假设参数为：AAAABBBEEFFGG 3我们先将出现次数最多N的元素占位好，中间空出n个位置
    //A_ _ _ A _ _ _ A _ _ _ A然后填充B,AB_ _AB_ _AB_ _A,ABE_ABE_ABE_A
    //ABEFABEFABEGABEGA。如果两个元素出现次数一样，那就看做一个整体，如下
    //ACCCEEE 2,    CE_CE_CE  ->CEACE_CE 有一个冷却时间
    //可以发现，模块个个数为N-1,比如A出现4次，模块A_ _ _一共有三个，而每个模块的长度
    //m=n+1，所以总个时间为(N-1)*m + 最后剩余的那部分长度，
    //比如第一个例子是A，所以加长度1；第二个是CE,所以加长度3
    //要注意如果是AAABCDEFGH 1（n较小）这种情况是ABACA DEFGH这个时候就不是5而是10了
    //也就是说完全不需要考虑冷却，所以应该取两者的较大值。
    // 解题思路：相同的任务之间至少隔了n个时间间隔。如果任务执行列表之间没有空闲，那么CPU从开始执行任务到结束，所需时间间隔至少为任务的总数。如果任务执行列表之间有空闲，那么所需时间间隔取决于最多的那个（些）任务。所以先统计每种任务的个数，对其进行排序，输出两种假设中的最大值。
    public int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];//统计各个字母出现的次数
		for(char c : tasks){
			map[c - 'A']++;
		}
		Arrays.sort(map);
		int max = map[25];
		int count = 0;
		for(int i = 0; i <= 25; i ++){
			if(map[i] == max) count++;
		}
		return Math.max(tasks.length, (max - 1) * (n + 1) + count);
    }
}
