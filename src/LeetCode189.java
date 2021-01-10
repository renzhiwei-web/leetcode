import java.util.Arrays;

public class LeetCode189 {
    /**
     * 旋转数组，此解法为环状数组求解
     * 将数组看成约瑟夫环，计算出每个元素在旋转之后的位置，每次使用一个临时变量temp来保存一个被占用位置的元素
     * 如果k和n的最大公约数是1，那么遍历一次数组即可（外循环只执行一次）
     * 如果 n % k = 0,那么将遍历多次，遍历的次数是n和k的最大公约数（证明：n=4,k=2,则n[0]->n[2],n[2]->n[0]）
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        k = k % n;
        int count = 0; // 记录交换位置的次数，n个元素一共需要交换n次
        for (int start = 0; count < n;start++) {
            int cur = start; // 从0位置开始换位子
            int pre = nums[cur]; // 要交换的元素
            do {
               int next = (cur + k) % n;
               int temp = nums[next]; // 暂存
               nums[next] = pre;
               pre = temp;
               cur = next;
               count++;
            }while (start != cur); // 当起始位置与终点位置相同，则结束内循环
        }

    }

    /**
     * 该方法基于如下的事实：当我们将数组的元素向右移动 kk 次后，
     * 尾部 k mod n 个元素会移动至数组头部，
     * 其余元素向后移动 k mod n 个位置。
     *
     * 该方法为数组的翻转：
     * 我们可以先将所有元素翻转，
     * 这样尾部的 k mod n个元素就被移至数组头部，
     * 然后我们再翻转 [0,k mod n−1] 区间的元素和[k mod n,n−1] 区间的元素即能得到最后的答案。
     *
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        k %= n;
        reverse(nums,0,n - 1);
        reverse(nums,0,k % n - 1);
        reverse(nums,k % n,n - 1);

    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
