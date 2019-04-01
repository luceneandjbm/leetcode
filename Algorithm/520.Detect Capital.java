class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.length() == 1) return true;
		char c = word.charAt(0);
		if(c >= 'A' && c <= 'Z') {
			int count = 0;
			for(int i = 1; i < word.length(); i ++) {
				char w = word.charAt(i);
				if(w >= 'a' && w <= 'z') count ++;
			}
			if(count != 0 && count != word.length() - 1) return false;
		}else {
			for(int i = 1; i < word.length(); i ++) {
				char w = word.charAt(i);
				if(w >= 'A' && w <= 'Z') return false; 
			}
		}
		return true;
    }
}
