import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode830 {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        int n = s.length();
        int num = 1;
        for (int i = 0;i < n;i++){
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)){
                if (num >= 3){
                    res.add(Arrays.asList(i - num + 1,i));
                }
                num = 1;
            } else {
                num++;
            }
        }
        return res;
    }

}
