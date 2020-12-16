import java.util.HashMap;
import java.util.Map;

public class LeetCode290 {
    /**
     * 使用双ha
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] strs = s.split(" ");
        if (strs.length != pattern.length()) {
            return false;
        }
        char[] array = pattern.toCharArray();

        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            if (map.containsKey(array[i])) {
                if (!map.get(array[i]).equals(strs[i])) {
                    return false;
                }
            } else {
                map.put(array[i], strs[i]);
            }
        }
        Map<String,Character> newMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            if (newMap.containsKey(strs[i])){
                if (!newMap.get(strs[i]).equals(array[i])){
                    return false;
                }
            } else {
                newMap.put(strs[i],array[i]);
            }
        }
        return true;
    }
}
