import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode684 {
    /**
     * 通过深度遍历的方式，对于每条边<u,v>,如果u可以连接到v，则它就是重复的边
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return new int[0];
        }
        int n = edges.length;
        Set<Integer>[] adjList = new Set[n + 1]; // 创建邻接表，使用邻接表表示图
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (adjList[from] == null) {
                adjList[from] = new HashSet<>();
            }
            adjList[from].add(to);
            if (adjList[to] == null) {
                adjList[to] = new HashSet<>();
            }
            adjList[to].add(from);
        }
        for (int i = n - 1; i >= 0; i--) {// 因为使用最后出现的边，所以使用逆序遍历
            int from = edges[i][0];
            int to = edges[i][1];
            adjList[from].remove(to); // 暂时删除正在遍历的这条边
            adjList[to].remove(from);
            if (dfs(from, to, adjList, new boolean[n + 1])) { // 因为节点是从1->n的，为了访问方便，数组的空间为n+1
                // 如果能从起点访问到终点，说明这条边是冗余的
                int[] res = {from, to};
                Arrays.sort(res);
                return res;
            }
            adjList[from].add(to);
            adjList[to].add(from);
        }
        return new int[0];
    }

    private boolean dfs(int start, int target, Set<Integer>[] adjList, boolean[] isVisited) {
        if (start == target) {
            return true;
        }
        isVisited[start] = true;
        if (adjList[start] != null) {
            for (Integer next : adjList[start]) {
                if (isVisited[next]) {
                    continue;
                }
                if (dfs(next, target, adjList, isVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    int[] result = new int[2];// 缓存结果集

    /**
     * 使用并查集
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection1(int[][] edges) {
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            union(parent,edge[0],edge[1]);
        }
        return result;
    }

    private void union(int[] parent, int x, int y) {
        int rootX = find(parent,x);
        int rootY = find(parent,y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        } else {
            result[0] = x;
            result[1] = y;
        }
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
