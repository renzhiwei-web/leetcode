import org.junit.Test;

public class LeetCode330 {
    /**
     * 可以这么理解，以[1,5,10]的例子为例: 我们从1开始遍历,并且维护一个指向nums的下标.一开始是1，
     * 而我们看到当前nums数组的第一个元素就是1,所以不需要其他操作.直接跳到2，并且让pos指向nums的第二个元素；
     *
     * 现在,我们的目标数是2,但是当前pos指向的数却是5,显然我们只能自己填充一个2,所以让res+1;
     * 既然我们已经填过2了,而在2之前可以被覆盖的最长区间长度是1,所以当前可以遍历到的最大区间长度变成了3(即2 + 1);
     *
     * 然后,我们可以忽略3,直接跳到4(因为上一步已经知道3在最大覆盖范围内了)。我们发现4同样比当前pos所指向的nums元素小,所以我们得填入4，即让res+1;
     * 既然已经填入4了,而我们知道在4之前可以覆盖的连续区间是(1-3),所以当前可以覆盖的最大区间被扩展到了7(即4 + 3)。
     *
     * 接下来我们可以直接跳过5、6、7来到8,而当前pos所指向的元素是5,所以当前可覆盖的区间大小又可以加上5了(7+5 = 12),并让pos指向下一个元素
     *
     * 最后我们跳过了7-12，从13开始遍历，这时候pos所指的元素是10,所以覆盖范围变成了12 + 10 = 22 >20，说明可以完全覆盖指定区间了！
     *
     * 到这里大概能够看出端倪 ：我们不断维持一个从1开始的可以被完全覆盖的区间,
     * 举个例子,当前可以完全覆盖区间是[1,k]，而当前pos所指向的nums中的元素为B,说明在B之前(因为是升序，所以都比B小)的所有元素之和可以映射到1-----k，
     * 而当我们把B也加入进去后，显然，可映射范围一定向右扩展了B个，也就是变成了1---k+B，这就是解题的思路
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        //累加的总和
        long total = 0;
        //需要补充的数字个数
        int count = 0;
        //访问的数组下标索引
        int index = 0;
        while (total < n) {
            if (index < nums.length && nums[index] <= total + 1) {
                //如果数组能组成的数字范围是[1,total]，那么加上nums[index]
                //就变成了[1,total]U[nums[index],total+nums[index]]
                //结果就是[1,total+nums[index]]
                total += nums[index++];
            } else {
                //添加一个新数字，并且count加1
                total = total + (total + 1);
                count++;
            }
        }
        return count;
    }

    @Test
    public void test(){
        int[] nums = {1,3};
        int n = 6;
        minPatches(nums,n);
    }
}
