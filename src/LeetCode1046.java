import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode1046 {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0;i < stones.length;i++){
            priorityQueue.offer(stones[i]);
        }

        int y = 0;
        int x = 0;
        while (priorityQueue.size() > 1){
            y = priorityQueue.poll();
            x = priorityQueue.poll();
            if (x == y){
                continue;
            } else if (x != y){
                y = y - x;
                priorityQueue.offer(y);
                continue;
            }
        }
        if (priorityQueue.size() == 1){
            return priorityQueue.peek();
        } else {
            return 0;
        }
    }

    @Test
    public void test(){
        int[] stones = {2,7,4,1,8,1};
        lastStoneWeight(stones);
    }
}
