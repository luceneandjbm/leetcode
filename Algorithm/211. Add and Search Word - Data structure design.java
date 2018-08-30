class WordDictionary {

	Map<Integer, Set<String>> map = new HashMap<>();
    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        int len = word.length();
		if(!map.containsKey(len)){
			map.put(len, new HashSet<String>());
		}
		map.get(len).add(word);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        int len = word.length();
		if(!map.containsKey(len))return false;
		Set<String> set = map.get(len);
		if(isWord(word)) return set.contains(word);
		for(String s : set){
			if(isMatch(word,s)) return true;
		}
		return false;
    }
	private boolean isMatch(String word, String s){
		for(int i = 0; i < s.length(); i ++){
			if(word.charAt(i)!='.' && word.charAt(i)!=s.charAt(i))
				return false;
		}
		return true;
	}
	private boolean isWord(String word){
		for(int i = 0; i < word.length(); i ++){
			if(word.charAt(i) == '.') return false;
		}
		return true;
	}
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
