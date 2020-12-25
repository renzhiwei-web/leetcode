public class LeetCode135 {

    /**
     * 使用两次遍历的方式，主要是比较每个人的前后相邻的人分数
     * 然后每个人的分的糖果数取两次遍历方式的最大值，这样，就符合相邻分数多的人分的糖果比较多
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]){ // 比较每个人与他前面的人的分数
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            if (i < len - 1 && ratings[i + 1] < ratings[i]){ // 比较每个人与他后面的人的分数
                right[i] = right[i + 1] + 1;
            }else {
                right[i] = 1;
            }
            res += Math.max(right[i],left[i]);
        }
        return res;
    }
}
