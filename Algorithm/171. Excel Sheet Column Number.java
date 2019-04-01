class Solution {
    // "B" = 2
    // "BC" = (2)26 + 3
    // "BCM" = (2(26) + 3)26 + 13
    public int titleToNumber(String s) {
        int ans = 0;
		for(char c : s.toCharArray()){
			ans *= 26;
			ans += c - 'A' + 1;
		}
		return ans;
    }
}
