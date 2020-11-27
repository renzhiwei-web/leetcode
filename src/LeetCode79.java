public class LeetCode79 {
    public boolean exist(char[][] board, String word) {
        for (int i = 0;i < board.length;i++){
            for (int j = 0;j < board[0].length;j++){
                if (helper(board,word,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param board
     * @param word
     * @param index 单词字母索引
     * @param i 坐标
     * @param j
     * @return
     */
    private boolean helper(char[][] board,String word,int index,int i,int j){
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (index == word.length() - 1){// 已经查询到最后一个单词
            if (board[i][j] == word.charAt(index)){
                return true;
            }
            return false;
        }
        // 如果不是最后一个单词
        if (board[i][j] == word.charAt(index)){
            board[i][j] = 1;
            if (helper(board, word, index + 1, i + 1, j))
                return true;
            else if (helper(board, word, index + 1, i, j + 1))
                return true;
            else if (helper(board, word, index + 1, i - 1, j))
                return true;
            else if (helper(board, word, index + 1, i, j - 1))
                return true;
            else{
                board[i][j] = word.charAt(index); //这个起点不符合，回滚操作
                return false;
            }
        }
        return false;
    }
}
