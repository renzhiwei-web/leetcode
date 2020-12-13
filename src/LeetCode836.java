/**
 * 第一种方法：排除掉不重合的条件就是重合的条件
 * 第二种方法：若两个矩形在坐标系上有交集，那么映射到坐标轴上也会有交集
 */
public class LeetCode836 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0]     // 矩形1在矩形2的左边
                || rec1[1] >= rec2[3]   // 矩形1在矩形2的上边
                || rec1[0] >= rec2[2]   // 矩形1在矩形2的右边
                || rec1[3] <= rec2[1]   // 矩形1在矩形2的下边
                || rec1[0] == rec1[2]   // rec1为一条竖线
                || rec1[1] == rec1[3]   // rec1为一条横线
                || rec2[0] == rec2[2]   // rec2为一条竖线
                || rec2[1] == rec2[3]); // rec2为一条横线
    }

    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2],rec2[2]) > Math.max(rec1[0],rec2[0]) &&  // x轴上，x2大于x1，说明有交集
                Math.min(rec1[3],rec2[3]) > Math.max(rec1[1],rec2[1]));   // y轴上，y2大于y1，说明有交集
    }
}
