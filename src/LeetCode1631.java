import java.util.*;

public class LeetCode1631 {
    /**
     * 二分查找+dfs（深度优先搜索遍历）
     * 将可能的体力值的范围作为二分查找的范围，找出其中的中间值，作为当前判断从左上角到右下角的可行路径的标准值
     * 若在当前的中间值下，不存在可能的路径，则说明需要更大的中间值，故调整左边界，反之，调整右边界
     *
     * @param heights
     * @return
     */
    public int minimumEffortPath(int[][] heights) {
        int[][] step = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int rows = heights.length;
        int columns = heights[0].length;
        // 确定的初始的二分范围
        int left = 0;
        int right = 999999; // 因为heights[i][j]<10^6
        while (left < right) { // 二分搜索
            boolean[][] sign = new boolean[rows][columns];
            // 当前的体力中间值
            int mid = left + (right - left) / 2;
            if (dfs(heights, sign, mid, 0, 0, step)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean dfs(int[][] heights, boolean[][] sign, int mid, int x, int y, int[][] step) {
        if (x == heights.length - 1 && y == heights[0].length - 1) { // 说明到达了右下角，直接返回true
            return true;
        }
        sign[x][y] = true; // 标记当前访问过的路径
        // 可能前进的方向
        for (int i = 0; i < 4; i++) {
            // 当前可能踏出的下一步
            int cur_x = x + step[i][0];
            int cur_y = y + step[i][1];
            // 前四个条件满足该位置有效，第五个条件保证当前点之前没有访问过，第六个条件保证两点之间满足体力值要求
            if (cur_x >= 0 && cur_x < heights.length && cur_y >= 0 && cur_y < heights[0].length
                    && !sign[cur_x][cur_y]
                    && Math.abs(heights[x][y] - heights[cur_x][cur_y]) <= mid) {
                if (dfs(heights, sign, mid, cur_x, cur_y, step)) {
                    return true;
                }
            }
        }
        return false;// 跳出循环，说明不能达到右下角
    }

    // 最短路径，基于迪杰斯特拉算法，将维护最小路径改为维护最小体力值
    public int minimumEffortPath1(int[][] heights) {
        int rows = heights.length;
        int columns = heights[0].length; // 路径上最小的体力值
        int[][] dist = new int[rows][columns];
        int[][] step = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]); // 使用优先级队列，每次优先获取最小体力值的节点
        boolean[][] isVisited = new boolean[rows][columns];
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            if (isVisited[x][y]) {
                continue;
            }
            isVisited[x][y] = true;
            for (int[] d : step) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < columns) {
                    int effort = Math.max(dist[x][y], Math.abs(heights[x][y] - heights[nx][ny])); // 更新当前节点在路径上的最小体力值
                    dist[nx][ny] = Math.min(dist[nx][ny], effort);
                    queue.offer(new int[]{nx, ny, dist[nx][ny]});
                }
            }
        }
        return dist[rows - 1][columns - 1];
    }

    // 并查集
    private int m;
    private int n;
    public int minimumEffortPath2(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        parent = new int[m * n];
        rank= new int[m * n];
        for (int i = 0;i < parent.length;i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m) {
                    // 合并上边和下边的节点
                    list.add(new Edge(i * n + j,(i + 1) * n + j,Math.abs(heights[i][j] - heights[i + 1][j])));
                }
                if (j + 1 < n) {
                    // 合并左边和右边的节点
                    list.add(new Edge(i * n + j,i * n + j + 1,Math.abs(heights[i][j] - heights[i][j + 1])));
                }
            }
        }
        Collections.sort(list,(a,b) -> a.value - b.value);
        int ret = 0;
        for (Edge edge : list) {
            if (isConnected(0 , m * n - 1)){ // 首尾节点是否能够连通
                break;
            }
            int v = edge.v;
            int w = edge.w;
            if (!isConnected(v,w)){
                union(v,w);
                ret = edge.value;
            }
        }
        return ret;
    }

    private class Edge{
        int v; // from的id
        int w; // to的id
        int value; // 距离
        Edge(int v,int w,int value){
            this.v = v;
            this.w = w;
            this.value = value;
        }
    }
    private int[] parent;
    private int[] rank;
    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    private boolean isConnected(int x,int y){
        return find(x) == find(y);
    }
    private void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        if (rank[rootX] < rank[rootY]){
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX] += 1;
        }
    }
}
