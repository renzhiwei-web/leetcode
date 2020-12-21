public class LeetCode389 {
    /**
     * 这道题共有两个思路：
     * 1：通过异或运算，因为两个字符串中只有一个字母是被添加的，那么只有这个字母出现奇数次，其余字母出现偶数次
     * 2：因为字符串中都是小写字母，所以通过将s与t中的字符转成数字后分别相加，求差即得解
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char res = 0;
        for (char c: s.toCharArray()) {
            res ^= c;
        }
        for (char c: t.toCharArray()) {
            res ^= c;
        }
        return res;
    }
}
