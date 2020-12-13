import org.junit.Test;

/**
 * 该题的思路是：通过映射到坐标系上的交集，来判断并计算计算重合矩形的面积，建议先完成839题
 */

public class LeetCode223 {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        long x = (long)Math.min(C, G) - Math.max(A, E); // 重合矩形的长
        long y = (long)Math.min(D, H) - Math.max(B, F); // 重合矩形宽
        int x1 = C - A;
        int y1 = D - B;
        int x2 = G - E;
        int y2 = H - F;
        if (x > 0 && y > 0) {
            return (int) ((x1 * y1) + (x2 * y2) - (x * y));
        } else {
            return (x1 * y1) + (x2 * y2);
        }
    }

    @Test
    public void test(){
        System.out.println(computeArea(-1500000001, 0, -1500000000, 1, 1500000000, 0, 1500000001, 1));
    }
}
