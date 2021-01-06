import org.junit.Test;

public class LeetCode547 {
    public int findCircleNum(int[][] isConnected) {
        int citys = isConnected.length;
        Integer[] parent = new Integer[citys];
        for (int i = 0; i <citys; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < citys; i++) {
            for (int j = i + 1; j < citys; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent,i,j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < citys; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }

    private void union(Integer[] parent, int i, int j) {
        parent[find(parent,i)] = find(parent,j);
    }

    private int find(Integer[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    @Test
    public void test(){
        int[][] isConnected = {{1,0,0,1}, {0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println(findCircleNum(isConnected));
    }
}
