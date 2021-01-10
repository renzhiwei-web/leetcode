import java.util.HashSet;
import java.util.Set;

public class LeetCode1319 {
    /**
     * 这个题实际考察的是如何将图变成一个无向连通图。要想将n个节点连通，则至少需要n-1条边
     * 遍历得到图中的连通量，如果图中的连通量为1，则无需移动线缆
     * 如果图中的连通量(k)>1,那么至少额外需要k-1根线缆
     * 如果给定的线缆不足n-1，则不能连成网络
     *
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        int length = connections.length;
        if (length < n - 1) {
            return -1;
        }
        Set<Integer>[] set = new Set[n]; // 定义邻接表来实现图
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            set[i] = new HashSet<>();
        }
        for (int[] connection : connections) {
            int var1 = connection[0];
            int var2 = connection[1];
            set[var1].add(var2);
            set[var2].add(var1);

        }
        int part = 0; // 连通分量
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                part++;
                dfs(i, used, set);
            }
        }
        return part - 1;
    }

    private void dfs(int i, boolean[] used, Set<Integer>[] set) {
        used[i] = true;
        for (int v : set[i]) {
            if (!used[v]) {
                dfs(v, used, set);
            }
        }
    }

    /**
     * 使用并查集实现
     *
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected1(int n, int[][] connections) {
        int len = connections.length;
        if (len < n - 1) {
            return -1;
        }
        UnionFind unionFind = new UnionFind(n);
        int cut = 0; // 多余的边的条数
        for (int[] connection : connections) {
            boolean success = unionFind.union(connection[0], connection[1]);
            if (!success) {
                cut++;
            }
        }
        return unionFind.count - 1;

    }

    private class UnionFind {
        private int[] parent;
        private int count; // 连通分量的个数

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            parent[rootX] = rootY;
            count--;
            return true;
        }

        private int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
    }

}
