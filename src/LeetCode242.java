import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode242 {
    public boolean isAnagram(String s, String t) {
        Set<String> set = new HashSet<>();
        int[] count = new int[26];
        Arrays.fill(count,0);
        for(char c:s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < 26;i++) {
            sb.append("#");
            sb.append(count[i]);
        }
        set.add(sb.toString());
        Arrays.fill(count,0);
        for(char c:t.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0;i < 26;i++) {
            sb1.append("#");
            sb1.append(count[i]);
        }
        return set.contains(sb1.toString());
    }
}
