public class LeetCode222 {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root.left) + helper(root.right) + 1;
    }

    public int helper(TreeNode node){
        if (node == null){
            return 0;
        }
        return helper(node.left) + helper(node.right) + 1;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}