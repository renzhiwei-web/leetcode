import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode216 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        helper(k,n,1,path,result);
        return result;
    }

    /**
     *
     * @param k        剩下要找k个数
     * @param residue  剩余多少
     * @param start    下一轮搜索的起始元素是多少
     * @param path     深度优先遍历的路径参数（状态变量）
     * @param res      保存结果集的列表
     */
    public void helper(int k, int residue, int start, Deque<Integer> path, List<List<Integer>> res){
        if (residue < 0){
            return;
        }
        if (k == 0) {
            if (residue == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            path.addLast(i);
            helper(k - 1,residue - i,i + 1,path,res);
            path.removeLast();
        }
    }
}
