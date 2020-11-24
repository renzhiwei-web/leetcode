public class LeetCode211 {

    private TrieNode root;

    /** Initialize your data structure here. */
    public LeetCode211() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(int i = 0;i < word.length();i++){
            if (node.children[word.charAt(i) - 'a'] == null){ //已有当前前缀
                node.children[word.charAt(i) - 'a'] = new TrieNode();
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        node.isEnd = true;// 当前节点已经结束
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchHelp(word,root);
    }

    public boolean searchHelp(String word,TrieNode root){
        TrieNode node = root;
        for(int i = 0;i < word.length();i++){
            if (word.charAt(i) == '.'){
                for (int j = 0;j < 26;j++){// 如果是。，那么通过递归匹配下个字符
                    if (node.children[j] != null){
                        if (searchHelp(word.substring(i + 1),node.children[j])){
                            return true;
                        }
                    }
                }
                return false;// 如果没有一个能匹配到，那就退出递归
            }
            if (node.children[word.charAt(i) - 'a'] == null){
                return false; //当前节点不存在
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        return node.isEnd;// 当前节点是否为某个单词的结束
    }

    class TrieNode{
        private TrieNode[] children;
        boolean isEnd;

        public TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
            for (int i = 0; i < 26; i++){
                children[i] = null;
            }
        }
    }
}
