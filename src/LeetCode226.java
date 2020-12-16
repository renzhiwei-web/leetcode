public class LeetCode226 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode node = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(node);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
