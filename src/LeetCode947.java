import java.util.HashSet;
import java.util.Set;

public class LeetCode947 {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        UnionFind unionFind = new UnionFind(20000);
        for (int[] stone : stones) {
            unionFind.union(stone[0],stone[1] + 10000);
        }
        Set<Integer> set = new HashSet<>();
        for (int[] stone:stones){
            set.add(unionFind.find(stone[0]));
        }
        return n - set.size();
    }

    private class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
        }

        private int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }
}
