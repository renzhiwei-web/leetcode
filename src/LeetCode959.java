import org.junit.Test;

public class LeetCode959 {
    /**
     * 将每个1*1的方格用对角线分成4个三角形，每个三角形就是一个独立的域
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid) {
        int len = grid.length;
        UnionFind unionFind = new UnionFind(4 * len * len); // 将每个1*1的方格分为4个部分
        for (int i = 0; i < len; i++) {
            char[] array = grid[i].toCharArray();
            for (int j = 0; j < array.length; j++) {
                int root = 4 * (i * len + j);
                if (array[j] != '/') {
                    unionFind.union(root + 0, root + 1);
                    unionFind.union(root + 2, root + 3);
                }
                if (array[j] != '\\') {
                    unionFind.union(root + 0, root + 3);
                    unionFind.union(root + 1, root + 2);
                }
                if (i + 1 < len) { // 如果不是最后一行，则向下合并
                    unionFind.union(root + 2, root + 4 * len + 0);
                }
                if (j + 1 < len) { // 如果不是最后一列，则向右合并
                    unionFind.union(root + 1, root + 4 + 3);
                }
            }
        }
        return unionFind.count;
    }

    private class UnionFind {
        private int[] parent;
        private int count; // 不连通域的个数

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            count = n;
        }

        private void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            count--;
        }

        private int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }

}
