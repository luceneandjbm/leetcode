class Trie {
	private TrieNode root;
	class TrieNode{
		int path;//经过该节点的路径
		int end;//以该节点结尾的路径
		TrieNode[] map;//该节点的分叉
		public TrieNode(){
			path = 0;
			end = 0;
			map = new TrieNode[26];
		}
	}
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word == null || word.equals("")) return;
		TrieNode node = root;
		for(int i = 0; i < word.length(); i ++){
			int idx = word.charAt(i) - 'a';
			if(node.map[idx] == null){
				node.map[idx] = new TrieNode();
			}
			node.path ++;//路径节点数+1
			node = node.map[idx];//更新node，往下一层
		}
		node.end ++;//单词数+1
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word == null || word.equals(""))return false;
		TrieNode node = root;
		for(int i = 0; i < word.length(); i ++){
			int idx = word.charAt(i) - 'a';
			if(node.map[idx] == null){
				return false;
			}
			node = node.map[idx];
		}
		return node.end != 0;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix == null || prefix.equals(""))return false;
		TrieNode node = root;
		for(int i = 0; i < prefix.length(); i ++){
			int idx = prefix.charAt(i) - 'a';
			if(node.map[idx] == null){
				return false;
			}
			node = node.map[idx];
		}
		return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
