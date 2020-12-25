import org.junit.Test;

import java.util.Arrays;

public class LeetCode387 {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0){
            return 0;
        }
        int[] count = new int[26];
        Arrays.fill(count,0);
        char[] array = s.toCharArray();
        for (int i = 0;i < array.length;i++){
            char ch = array[i];
            count[ch - 'a']++;
        }
        for (int i = 0;i < array.length;i++){
            if(count[array[i] - 'a'] == 1){
                return i;
            }
        }
        return -1;
    }

    @Test
    public void methodTest(){
        firstUniqChar("leetcode");
    }
}
