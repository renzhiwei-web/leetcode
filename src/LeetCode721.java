import java.util.*;

public class LeetCode721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind unionFind = new UnionFind();
        Map<String,String> emailToName = new HashMap<>(); // key是email地址，value是所属账户名称
        Map<String,Integer> emailToId = new HashMap<>(); // key是email地址，value是整数编号
        int id = 0;
        for (List<String> account : accounts) {
            String name = ""; // 账户名称
            for (String email :account) {
                if (name == "") { // 如果账户名称是空串，那么当前访问的字符串就是账户名称
                    name = email;
                    continue;
                }
                emailToName.put(email,name);
                if (!emailToId.containsKey(email)) {
                    emailToId.put(email,id++);
                }
                // 将当前账户的第一个邮件地址与其他的邮件地址进行合并
                unionFind.union(emailToId.get(account.get(1)),emailToId.get(email));
            }
        }
        Map<Integer,List<String>> ans = new HashMap<>(); // key是email地址的整数编号，value是相同账户下所有的email地址
        for (String email : emailToName.keySet()) {
            int index = unionFind.find(emailToId.get(email));
            ans.computeIfAbsent(index,key -> new ArrayList<>()).add(email);
        }
        for (List<String> component : ans.values()) {
            Collections.sort(component); // 对每个地址进行字典排序
            component.add(0,emailToName.get(component.get(0))); // 再集合的头部添加账户的名称
        }
        return new ArrayList<>(ans.values());
    }
    private class UnionFind{
        int[] parent;
        public UnionFind(){
            parent = new int[10001];
            for (int i = 0; i <= 10000; i++) {
                parent[i] = i;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x,int y){
            parent[find(x)] = find(y);
        }
    }
}
