import java.util.LinkedList;
import java.util.Queue;

/**
 * 如果均不为空，那么比较这两个队列的首元素，就可以确定当前行使权利的是哪一名议员。
 * 如果 radiant 的首元素较小，那说明轮到天辉方的议员行使权利，其会挑选 dire 的首元素对应的那一名议员。
 * 因此，我们会将 dire 的首元素永久地弹出，并将 radiant 的首元素弹出，增加 nn 之后再重新放回队列，
 * 这里 n 是给定的字符串 senate 的长度，即表示该议员会参与下一轮的投票。
 */
public class LeetCode649 {
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }

        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll();
            int direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }
}
