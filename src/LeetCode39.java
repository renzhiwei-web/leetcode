import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LeetCode39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        if (len == 0){
            return result;
        }
        helper(candidates,0,target,path,result);
        return result;
        /*
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
         */
    }

    /**
     *
     * @param candidates 数据源
     * @param index      数据源索引
     * @param target     剩下的目标值
     * @param stack      深度优先遍历的路径参数（状态变量）
     * @param result     结果集
     */
    public void helper(int[] candidates,int index,int target,Deque<Integer> path,List<List<Integer>> result){
        if (target < 0) {
            return;
        }
        if (target == 0){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = index;i < candidates.length;i++){
            path.addLast(candidates[i]);
            helper(candidates, i, target - candidates[i], path, result);
            path.removeLast();
        }

    }

    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfs(candidates, i, len, target - candidates[i], path, res);
            path.removeLast();
        }
    }

    @Test
    public void test(){
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(combinationSum(candidates,target));
    }
}
