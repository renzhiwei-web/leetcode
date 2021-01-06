import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize); // 因为每个数组表示两个节点，所以使用两倍的容量
        // 第一步：预处理，将变量的值与id进行映射，是得并查集的底层使用数组实现，方便编码
        Map<String,Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0;i < equationsSize;i++){
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1,id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2,id);
                id++;
            }
            unionFind.union(hashMap.get(var1),hashMap.get(var2),values[i]);
        }

        // 第二步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0;i < queriesSize;i++){
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1,id2);
            }

        }
        return res;
    }

    private class UnionFind {
        private int[] parent; // 当前节点的父节点

        private double[] weight; // 指向的父节点的权值

        public UnionFind(int n){
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0;i < n;i++){
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        /**
         * 将两个节点进行合并，x表示分子，y表示分母
         * @param x
         * @param y
         * @param value
         */
        public void union(int x,int y,double value){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) { // 如果两个x和y的根节点是同一个，则不需要合并
                return;
            }
            parent[rootX] = rootY; // x的根节点的父节点是y的根节点
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         * @param x
         * @return 根节点的id
         */
        private int find(int x) {
            if (x != parent[x]) { // 如果当前节点的父节点不是自己，那么通过递归，寻找到父节点的根节点
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin]; // 指向根节点的权值
            }
            return parent[x];
        }

        /**
         * 判断两个节点是否是连接的
         * @param x
         * @param y
         * @return
         */
        public double isConnected(int x,int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
