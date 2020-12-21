import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode316 {
    public String removeDuplicateLetters(String s) {
        char[] array = s.toCharArray();
        int[] lastIndex = new int[26];
        for (int i = 0; i < array.length; i++) {
            lastIndex[array[i] - 'a'] = i;
        }
        boolean[] visited = new boolean[26]; // 保存字母是否已经进栈
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {
            if (visited[array[i] - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.peekLast() > array[i] && lastIndex[stack.peekLast() - 'a'] > i) {
                // 如果当前访问元素小于栈顶元素并且当前栈顶元素在以后还会出现，那么就删除栈顶元素
                Character top = stack.removeLast();
                visited[top - 'a'] = false;
            }
            stack.addLast(array[i]);
            visited[array[i] - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }
}
