import java.util.PriorityQueue;

public class LeetCode451 {
    public String frequencySort(String s) {
        int[] letters = new int[128];
        for (char c : s.toCharArray()) letters[c]++;

        PriorityQueue<Character> heap = new PriorityQueue<>(128, (a, b) -> Integer.compare(letters[b], letters[a]));
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < letters.length; ++i) {
            if (letters[i] != 0) {
                heap.offer((char) i);
            }
        }

        while (!heap.isEmpty()) {
            char c = heap.poll();
            while (letters[c]-- > 0) {
                res.append(c);
            }
        }
        return res.toString();
    }
}
