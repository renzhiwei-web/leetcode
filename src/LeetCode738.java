public class LeetCode738 {
    /**
     * 这道题使用贪心算法
     * 依次比较arr[i]与arr[i+1],如果arr[i]>arr[i+1]，那么就将截至arr[i]之前的数字中的最大那一位arr[maxIndex] - 1,再将arr[maxIndex]之后所有的数字变成9即可
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        char[] arr = (N + "").toCharArray();
        int max = -1;
        int maxIndex = -1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > max) {
                max = arr[i];
                maxIndex = i;
            }
            if (arr[i] > arr[i + 1]) {
                arr[maxIndex] -= 1;
                for (int j = maxIndex + 1; j < arr.length; j++) {
                    arr[j] = '9';
                }
                break;
            }
        }
        return Integer.parseInt(new String(arr));
    }
}
