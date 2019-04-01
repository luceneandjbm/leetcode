//定义一个sum变量，表示当前拥有的油量，假设最开始0可以。我们从第一个加油站开始计算，如果发现sum<0
//说明就不能走到这个加油站，此时就假设可以的位置为i+1(sum_i<0是说明i位置不能走到i+1位置，
//显然0-i的所有位置都不能走到i+1,比如中间的k位置能走到i+1，那么i能走到k自然也能走到i+1
//所以0-i之间的所有位置都不行，所以设置下一个假设可以的位置是i+1)。同时我们需要计算
//total(总的油量-总得消费量)只有大于0的时候才存在能环绕的位置，否则返回-1。因为如果能走完
//前面的都不行能肯定最后又一个是可以的，也就是我我们假设的位置
//注：不用担心j==gas.length的情况，因为出现这种情况说明根本走不完全程，即totalGas<0
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
		int sum = 0;
		int start = 0;//假设0位置可以
		for(int i = 0; i < gas.length; i ++){
			sum += gas[i] - cost[i];
			if(sum < 0){//i位置不能到i+1位置了
				start = i + 1;
				sum = 0;
			}
			total += gas[i] - cost[i];
		}
		return total>=0?start:-1;
    }
}
