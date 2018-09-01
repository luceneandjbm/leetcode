class Solution {//7,3  为什么用long？因为1/Integer.MAX_VALUE会导致溢出
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";
		StringBuilder sb = new StringBuilder();
		//确定符号
		if((numerator>0&&denominator<0)||(numerator<0&&denominator>0)){
			sb.append("-");
		}
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        sb.append(num/den);
		if(num%den ==0){
			return sb.toString();
		}
		sb.append(".");
		num%=den;
		HashMap<Long, Integer> map = new HashMap<>();
		map.put(num,sb.length());
		while(num != 0) {
			num *= 10;
			sb.append(num/den);
			num %= den;
			if(map.containsKey(num)){
				int index = map.get(num);
				sb.insert(index,"(");
				sb.append(")");
				break;
			}else{
				map.put(num,sb.length());
			}
		}
		return sb.toString();
    }
}
