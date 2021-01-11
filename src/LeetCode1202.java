import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LeetCode1202 {
    /**
     * 本题看似是字符串问题，实则是并查集
     * 因为每一个可以交换的索引可以使用多此，通过将可以交换的索引进行合并，可以得到索引的连通量，在这个连通量中，字符数组可以进行字典排序，再通过索引插入，即可求解
     *
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> list : pairs) {
            int var1 = list.get(0);
            int var2 = list.get(1);
            unionFind.union(var1,var2);
        }
        Map<Integer, PriorityQueue<Character>> hashmap = new HashMap<>(len);
        char[] array = s.toCharArray();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            if (hashmap.containsKey(root)) {
                hashmap.get(root).offer(array[i]);
            } else {
//                PriorityQueue<Character> priorityQueue = new PriorityQueue<Character>();
//                priorityQueue.offer(array[i]);
//                hashmap.put(root,priorityQueue);
//                以上代码可以由下面的一行代码就行替换
                hashmap.computeIfAbsent(root, key ->new PriorityQueue<Character>()).offer(array[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            sb.append(hashmap.get(root).poll());
        }
        return sb.toString();
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
