import java.util.Arrays;
import java.util.Comparator;

public class LeetCode435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0){
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {// 对数组按end进行排序
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 1; // 不相交的区间个数，至少有一个区间不相交
        int min = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            if (start >= min){
                count++;
                min = intervals[i][1];
            }
        }
        return intervals.length - count;
    }
}
