package com.flydean.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author wayne
 * @version No104maxDepth,  2020/8/26
 *
 * 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class No104maxDepth {

    /**
     * 方法一：递归
     * 思路与算法
     *
     * 如果我们知道了左子树和右子树的最大深度 ll 和 rr，那么该二叉树的最大深度即为
     * max(l,r)+1
     *
     * 而左子树和右子树的最大深度又可以以同样的方式进行计算。
     * 因此我们在计算当前二叉树的最大深度时，可以先递归计算出其左子树和右子树的最大深度，
     * 然后在 O(1) 时间内计算出当前二叉树的最大深度。递归在访问到空节点时退出。
     */

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


    /**
     * 方法二：广度优先搜索
     * 思路与算法
     *
     * 我们也可以用「广度优先搜索」的方法来解决这道题目，但我们需要对其进行一些修改，
     * 此时我们广度优先搜索的队列里存放的是「当前层的所有节点」。
     * 每次拓展下一层的时候，不同于广度优先搜索的每次只从队列里拿出一个节点，我们需要将队列里的所有节点都拿出来进行拓展，
     * 这样能保证每次拓展完的时候队列里存放的是当前层的所有节点，
     * 即我们是一层一层地进行拓展，最后我们用一个变量 ans 来维护拓展的次数，该二叉树的最大深度即为 ans。
     *
     */

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
