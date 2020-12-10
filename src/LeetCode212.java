import java.util.ArrayList;
import java.util.List;

public class LeetCode212 {

    private TrieNode root;
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();

        for (String word : words) {
            if (exist(board, word)) {
                res.add(word);
            }
        }
        /*
        getTrie(words);//创建前缀树
        for (int i = 0;i < board.length;i++){
            for(int j = 0;j < board[0].length;i++){
                if (helper(board,root.get(board[i][j]),i,j)) {
                    res.add()
                }
            }
        }*/
        return res;

    }

    /**
     * 直接使用79题中的图搜索代码，暴力解法
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board
     * @param word
     * @param index 单词字母索引
     * @param i     坐标
     * @param j
     * @return
     */
    private boolean helper(char[][] board, String word, int index, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (index == word.length() - 1) {// 已经查询到最后一个单词
            if (board[i][j] == word.charAt(index)) {
                return true;
            }
            return false;
        }
        // 如果不是最后一个单词
        if (board[i][j] == word.charAt(index)) {
            board[i][j] = 1;
            if (helper(board, word, index + 1, i + 1, j)) {
                board[i][j] = word.charAt(index);
                return true;
            } else if (helper(board, word, index + 1, i, j + 1)) {
                board[i][j] = word.charAt(index);
                return true;
            } else if (helper(board, word, index + 1, i - 1, j)) {
                board[i][j] = word.charAt(index);
                return true;
            } else if (helper(board, word, index + 1, i, j - 1)) {
                board[i][j] = word.charAt(index);
                return true;
            } else {
                board[i][j] = word.charAt(index); //这个起点不符合，回滚操作
                return false;
            }
        }
        return false;
    }

    /**
     * 通过单词列表中所有的单词构建前缀树，返回前缀树的头结点
     * @param words
     * @return
     */
    public void getTrie(String[] words){
        root = new TrieNode();
        for (String word : words) {
            TrieNode curNode = root;
            for(int i = 0;i < word.length();i++){
                char curChar = word.charAt(i);
                if (!curNode.containsKey(curChar)){
                    curNode.put(curChar,new TrieNode()); //讲curChar字符放在新创建的TrieNode中
                }
                curNode = curNode.get(curChar);
            }
            curNode.setEnd();
        }
    }


    /**
     * 深度优先
     *
     */
    private boolean helper(char[][] board, TrieNode curNode,int i,int j){
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        char ch = board[i][j];
        if (ch == 1 || curNode.isEnd){
            return false;
        }
        if (curNode.containsKey(board[i][j])) { //网格上当前坐标上的字符与当前节点的字符相同，比较字节的字符
            board[i][j] = 1;
            if (helper(board, curNode.get(ch), i + 1, j)) {
                return true;
            }else if (helper(board, curNode.get(ch), i, j + 1)){
                return true;
            }else if (helper(board, curNode.get(ch), i - 1, j)){
                return true;
            }else if (helper(board, curNode.get(ch), i, j - 1)){
                return true;
            }else{
                board[i][j] = ch;
                return false;
            }
        }
        return false;
    }
    /**
     * 前缀树
     */
    class TrieNode {

        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch -'a'] != null;
        }
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }
}
