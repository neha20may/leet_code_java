package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        Integer val;
    }
    int findHt(TreeNode node) {

        if (node.left == null && node.right == null) {
            return 0;
        }
        int lht = 0;
        int rht = 0;
        if (node.left != null) {
            lht = findHt(node.left);
        }
        if (node.right != null) {
            rht = findHt(node.right);
        }
        int ht = Math.max(lht, rht) + 1;
        return ht;

    }

    public int deepestLeavesSum(TreeNode root) {
        int ht = findHt(root);
        System.out.println("ht= " + ht);

        ArrayDeque<TreeNode> q = new ArrayDeque<TreeNode>();

        q.add(root);
        int level = 0;
        TreeNode marker = new TreeNode();
        int res = 0;
        List<TreeNode> lnodes = new ArrayList();
        lnodes.add(root);
        lnodes.add(marker);

        while (!q.isEmpty()) {
            if (q.peekLast() == marker) {
                level++;
                q.pollFirst();
                q.add(marker);
            }
            if (level == ht) {
                for (int i = 0; i < lnodes.size(); i++) {
                    res = res + lnodes.get(i).val;
                }
                lnodes.clear();
            }

            TreeNode node = q.pollFirst();

            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }

        }

        return res;

    }
}