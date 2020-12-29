public class LeetCode85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[][] width = new int[matrix.length][matrix[0].length];
        int maxArea = 0;
        for(int i = 0;i < matrix.length;i++){
            for(int j = 0;j < matrix[0].length;j++){
                if (matrix[i][j] == '1') { // 判断当前的‘1’是否是第一列的
                    if (j == 0) {
                        width[i][j] = 1; // 如果当前的‘1’是第一列的的则重新计数
                    }else {
                        width[i][j] = width[i][j - 1] + 1; // 如果当前的‘1’不是第一列的则在前面的基础上+1
                    }
                }else {
                    width[i][j] = 0;
                }
                int midWidth = width[i][j]; // 记录所有行的最小数，也就是当前矩形的宽
                for(int row = i;row >= 0;row--){
                    int height = i - row + 1;
                    midWidth = Math.min(midWidth, width[row][j]);
                    maxArea = Math.max(maxArea, midWidth * height);
                }
            }
        }

        return maxArea;
    }
}
